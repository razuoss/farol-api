# skills.md (AI Agent Skills & Guidelines)

> **Nota**: Este documento é um alias de configuração para compatibilidade com assistentes e agents que procuram por `skills.md`. A especificação primária e completa de regras arquiteturais, convenções de código e restrições para IA está mantida em [`AGENTS.md`].

---

## Quick Reference (Resumo Executivo para IA / Devs)
- Consulte o arquivo **[AGENTS.md]** na raiz do repositório para ler as regras invioláveis de arquitetura (Hexagonal - Ports & Adapters), proibições estritas, convenções de nomenclatura e links para os ADRs.
- O arquivo principal `AGENTS.md` está em inglês para otimização de tokens e maior fidelidade no seguimento de instruções por modelos LLM, preservando o vocabulário teológico de domínio em português.

### Regras de Ouro (Fast Check)
1. Nunca importe Spring Boot, bibliotecas HTTP ou SDKs dentro da camada de domínio.
2. Chamadas ao Gemini são síncronas (`HTTP POST`) sobre Virtual Threads.
3. Falhas de gravação no Google Sheets/Audit nunca devem quebrar ou alterar a resposta `HTTP 200 OK`.
4. Sempre utilizar Java `record` para DTOs e parsing de JSON Schema do Gemini — nunca use `Map<String, Object>`.
5. Nunca envie texto raw do usuário para o Gemini sem sanitização, limite de caracteres (max 1000) e validação de escopo.