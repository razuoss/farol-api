package io.github.razuoss.farol_da_fe.poc.infrastructure.out;

import io.github.razuoss.farol_da_fe.poc.application.PocGenerativeAiPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * ADAPTER DE SAÍDA (Outbound Adapter)
 * Implementa a Porta definida pela Aplicação. 
 * É aqui e SÓ AQUI que o código sabe que estamos usando a API do Google Gemini.
 */
@Component
public class PocGeminiAdapter implements PocGenerativeAiPort {

    private final RestClient restClient;
    private final String apiKey;

    public PocGeminiAdapter(@Value("${gemini.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.create("https://generativelanguage.googleapis.com/v1beta/models");
    }

    @Override
    public String generateResponse(String prompt) {
        String systemInstruction = """
                Você é um tutor de exegese e hermenêutica bíblica de viés protestante histórico e reformado.
                Seu objetivo não é dar conselhos pastorais, mas sim educar na leitura do texto sagrado.
                
                Sua resposta deve SEMPRE, na medida do possível:
                1. Fornecer o contexto histórico e cultural.
                2. Fazer uma análise literária e gramatical básica.
                3. Demonstrar a coerência com a Teologia Bíblica, assumindo a Bíblia como inerrante e suficiente.
                
                EVITE alegorias ou analogias rasas. Seja claro, profundo e muito didático.
                """;

        Map<String, Object> requestBody = Map.of(
                "systemInstruction", Map.of("parts", List.of(Map.of("text", systemInstruction))),
                "contents", List.of(Map.of("parts", List.of(Map.of("text", prompt))))
        );

        try {
            // Perceba que mantivemos o "generateContent". Veja a explicação no chat do motivo!
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restClient.post()
                    .uri("/gemini-3.1-flash-lite:generateContent?key={key}", apiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(Map.class); // Recebe como Map para fazer o parse manual do JSON

            // Parse manual (Navegando pela estrutura do JSON que vimos no seu print)
            // Em produção, isso seria feito automaticamente mapeando para DTOs como GeminiResponseDto
            if (response != null && response.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                if (!candidates.isEmpty()) {
                    Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    return (String) parts.get(0).get("text"); // Retorna SOMENTE o texto final limpo
                }
            }
            return "A IA não retornou um conteúdo válido.";

        } catch (Exception e) {
            // Tratamento genérico para não quebrar a aplicação na POC
            return "Erro na comunicação com a IA: " + e.getMessage();
        }
    }
}
