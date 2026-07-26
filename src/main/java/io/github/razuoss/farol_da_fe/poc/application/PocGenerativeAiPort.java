package io.github.razuoss.farol_da_fe.poc.application;

/**
 * PORTA DE SAÍDA (Outbound Port)
 * Define O QUE a aplicação precisa (gerar resposta de texto baseada em IA), 
 * sem importar COMO isso será feito (Gemini, OpenAI, etc).
 */
public interface PocGenerativeAiPort {
    String generateResponse(String prompt);
}
