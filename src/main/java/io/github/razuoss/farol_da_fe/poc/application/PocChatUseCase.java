package io.github.razuoss.farol_da_fe.poc.application;

import org.springframework.stereotype.Service;

/**
 * CASO DE USO (Use Case) - Camada de Aplicação / Domínio
 * Aqui fica a regra de negócio. Veja que ela NÃO sabe que é uma requisição HTTP
 * e NÃO sabe que está usando o Google Gemini. Ela só conhece as "Portas".
 */
@Service
public class PocChatUseCase {

    private final PocGenerativeAiPort aiPort;

    // Injeção de dependência da Porta
    public PocChatUseCase(PocGenerativeAiPort aiPort) {
        this.aiPort = aiPort;
    }

    public String askQuestion(String question) {
        // Regra de negócio simples (Validação)
        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("A pergunta não pode ser vazia.");
        }

        // Chama a porta de saída para processar a IA
        return aiPort.generateResponse(question);
    }
}
