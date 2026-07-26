package io.github.razuoss.farol_da_fe.poc.infrastructure.in;

import io.github.razuoss.farol_da_fe.poc.application.PocChatUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * ADAPTER DE ENTRADA (Inbound Adapter)
 * Ponto de contato com o mundo externo (REST). 
 * Ele recebe a chamada, delega para o UseCase e formata a resposta final.
 */
@RestController
@RequestMapping("/v1/poc/chat")
public class PocChatController {

    private final PocChatUseCase useCase;

    // Injeção de dependência do Use Case
    public PocChatController(PocChatUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> chat(@RequestBody Map<String, String> request) {
        
        String userQuestion = request.getOrDefault("pergunta", "");
        
        // Chama a regra de negócio (Use Case) que fará a orquestração
        String answer = useCase.askQuestion(userQuestion);
        
        // Formata a resposta como se fosse enviar de volta para o Telegram ou Front-end
        return Map.of("resposta_formatada", answer);
    }
}
