# Farol da Fé — Prompts & System Instructions Spec

Para evitar duplicação entre documentação e código executável, as especificações oficiais e ativas dos prompts de sistema (`system_instruction`) estão localizadas em **`src/main/resources/prompts/`**:

- **[exegese-system-prompt.md](file:///home/felipe/dev/work/projetos/farol-api/src/main/resources/prompts/exegese-system-prompt.md)**: Prompt de sistema oficial para o serviço de exegese bíblica, estudos e devocionais (`POST /v1/exegese`).

## Diretrizes de Manutenção
1. **Edição Única**: Modifique diretamente o arquivo em `src/main/resources/prompts/`. Ele é carregado pela aplicação Spring Boot no boot via ResourceLoader e enviado ao Google Gemini.
2. **Inglês com Saída em PT-BR**: O prompt é mantido em inglês para otimização de tokens (~30-40% menos tokens) e maior rigor no cumprimento de restrições por modelos LLM, mantendo instruções explícitas para saída em Português Brasileiro (PT-BR) alinhada à cultura cristã brasileira.
