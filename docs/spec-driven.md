# Spec-Driven Development (SDD) Guidelines

Este documento oficializa o processo de desenvolvimento e ciclo de vida de User Stories, integrando as regras do projeto e as ferramentas utilizadas (ex: GitHub Projects). Todo Agente IA, Desenvolvedor ou Ferramenta deve seguir esse fluxo obrigatoriamente.

## 1. Regras do GitHub Projects e Status de Issues

Ao gerenciar o desenvolvimento de features (User Stories) e outras issues no repositório, as seguintes transições de estado devem ser obedecidas no board do GitHub Projects:

### 1.1 Início do Desenvolvimento ("In Progress")
Ao pegar uma Issue (US ou Tarefa) para iniciar a implementação:
1. **Mover a Issue para "In Progress":** Atualize o status da Issue no painel do GitHub Projects.
2. **Associar/Criar Branch:** Vá para a seção "Development" da Issue e clique em "Create a branch" (ou crie a branch e faça o link dela com a issue).
3. **Iniciar os Trabalhos:** Só comece a desenvolver (modificar código ou arquivos locais) APÓS ter a branch associada à issue sob o status de "In Progress".

### 1.2 Conclusão e Pull Request ("In Review")
Após finalizar a implementação e realizar o Pull Request para a branch `develop`:
1. **Mover a Issue para "In Review":** Atualize o status da Issue no painel.
2. **Validar Checklists (Acceptance Criteria):** Acesse a Issue original e marque todos os checkboxes de critérios de aceite e tarefas (`- [x]`) que foram efetivamente concluídos.
3. **Justificar Pendências:** Caso algum item da checklist não tenha sido implementado (por mudança de escopo, dependência externa, erro, etc.), **adicione um comentário na Issue** informando claramente a justificativa.

## 2. Artefatos de Especificação (SPEC.md)

Na fase de especificação (pré-código) de uma nova funcionalidade, deve-se gerar o documento `SPEC.md` ou equivalente. As diretrizes para essa especificação são:
1. Deve ser clara e listar os Critérios de Aceite.
2. Deve orientar a criação dos testes E2E e Unitários.
3. **Diagrama de Sequência (Mermaid):** OBRIGATORIAMENTE, a seção final de todo documento de especificação gerado deve conter um Diagrama de Sequência em formato `mermaid` que ilumine as integrações, o fluxo de dados entre os componentes (Ports, Adapters, Usecases) e a experiência de uso.
