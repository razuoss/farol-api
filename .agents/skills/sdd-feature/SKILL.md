---
name: sdd-feature
description: Executa o fluxo completo de Spec-Driven Development (SDD) e TDD para a criação de uma nova funcionalidade (User Story).
---

# Diretrizes da Skill `sdd-feature`

Quando o usuário invocar esta skill, este fluxo deve ser seguido para implementar a funcionalidade:

1. **Planejamento Técnico (`/plan`)**:
   - Analise os requisitos da funcionalidade.
   - Siga rigorosamente `docs/spec-driven.md` movendo o status da Issue para "In Progress" e vinculando a nova branch.
   - Gere um artefato de plano de implementação listando pacotes, classes, interfaces e testes que serão criados.
   - Aguarde a aprovação do usuário.

2. **Especificação Local (Co-location e Estrutura Híbrida)**:
   - Respeite a estrutura híbrida do domínio: elementos exclusivos vão para o pacote da feature (`domain/nome_feature/`), enquanto elementos reutilizáveis vão para `domain/shared/`.
   - Crie um arquivo `SPEC.md` dentro do pacote da funcionalidade detalhando os Critérios de Aceite (formato Gherkin/BDD) baseados no `product.md`. Conforme `docs/spec-driven.md`, gere sempre um **Diagrama de Sequência (Mermaid)** na seção final.

3. **Test-Driven Development (TDD)**:
   - Escreva os testes unitários e de integração ANTES do código de produção.
   - Use JUnit 5, Mockito, AssertJ e WireMock (se houver integrações HTTP).
   - **Cenários Mínimos Obrigatórios (Exija a cobertura antes de concluir):**
     - **Testes Unitários:** Happy path (entrada válida), valores limite/máximo, entrada nula/vazia, caracteres especiais.
     - **Testes de Integração:** 200 OK (valido), 400 (inválido / injection), 429 (rate limit), 503 (IA down), 504 (timeout IA), 500 (schema mismatch).

4. **Implementação de Código**:
   - Crie as classes e interfaces respeitando a Arquitetura Hexagonal (Portas e Adaptadores).
   - Use `records` para DTOs.
   - Implemente os Guardrails se a feature envolver entrada do usuário.

5. **Testes E2E Locais (Podman e Bruno)**:
   - Compile a aplicação ignorando os testes unitários (`mvn clean package -DskipTests`).
   - Construa a imagem Podman com a tag combinando o nome do app e o código da US, ex: `farol-api-us001` (`podman build -t <nome> .`).
   - Crie a coleção de requisições mínimas e Health Check para o Bruno API Client na pasta `bruno/`.
   - Suba o container Podman em background expondo as portas necessárias.
   - Execute os testes automaticamente através do CLI do Bruno (`bru run`).

6. **Validação e Fluxo CI/CD**:
   - **PAUSE AQUI**: Aguarde o teste manual e aprovação (GO / NO-GO) por parte do humano. Não siga adiante sem o GO.
   - Após receber o GO:
     - Formate o commit no padrão `(tipo) descrição curta em pt-br` adicionando a tag `Co-authored-by: Antigravity AI <ai@antigravity.dev>`.
     - Suba as alterações para a branch da feature (ex: `feat/us-001-core-api`).
     - Monitore a esteira/workflow no GitHub (ex: Snapshot, Sonar).
     - Se o workflow passar, abra o Pull Request (PR) com título claro e resumo legível das alterações.
     - Siga `docs/spec-driven.md`: Mova a Issue para "In Review" no painel, marque os checkboxes dos critérios implementados e adicione comentários sobre o que faltou.
     - Finalize a tarefa parando o container (`podman rm -f`) e excluindo a imagem criada (`podman rmi`), mas mantenha a pasta e coleção do Bruno no repositório.
