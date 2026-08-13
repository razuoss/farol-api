---
name: sdd-feature
description: Executa o fluxo completo de Spec-Driven Development (SDD) e TDD para a criação de uma nova funcionalidade (User Story).
---

# Diretrizes da Skill `sdd-feature`

Quando o usuário invocar esta skill, este fluxo deve ser seguido para implementar a funcionalidade:

1. **Planejamento Técnico (`/plan`)**:
   - Analise os requisitos da funcionalidade.
   - Gere um artefato de plano de implementação listando pacotes, classes, interfaces e testes que serão criados.
   - Aguarde a aprovação do usuário.

2. **Especificação Local (Co-location e Estrutura Híbrida)**:
   - Respeite a estrutura híbrida do domínio: elementos exclusivos vão para o pacote da feature (`domain/nome_feature/`), enquanto elementos reutilizáveis vão para `domain/shared/`.
   - Crie um arquivo `SPEC.md` dentro do pacote da funcionalidade detalhando os Critérios de Aceite (formato Gherkin/BDD) baseados no `product.md`.

3. **Test-Driven Development (TDD)**:
   - Escreva os testes unitários e de integração ANTES do código de produção.
   - Use JUnit 5, Mockito, AssertJ e WireMock (se houver integrações HTTP).

4. **Implementação de Código**:
   - Crie as classes e interfaces respeitando a Arquitetura Hexagonal (Portas e Adaptadores).
   - Use `records` para DTOs.
   - Implemente os Guardrails se a feature envolver entrada do usuário.

5. **Artefatos Auxiliares**:
   - Gere a requisição para o Bruno API Client na pasta `collections/` na raiz do projeto.
