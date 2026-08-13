# Guia de Padrões e Contribuições - Farol da Fé

Agradecemos o seu interesse no projeto Farol da Fé!

**Status Atual do Projeto:**
atualmente, o projeto está em sua fase inicial de desenvolvimento (MVP), sendo construído por seu mantenedor principal. Por isso, **não estamos aceitando contribuições externas de código**, pois o objetivo agora é estabelecer uma base sólida e uma primeira versão funcional que servirá como peça de portfólio.

No futuro, após o lançamento do MVP, o projeto poderá ser aberto para colaboração.



## 🌿 Fluxo de Trabalho (GitFlow Simplificado)

Quando as contribuições forem abertas, seguiremos um fluxo de trabalho simplificado baseado no GitFlow.

1.  **Sincronize sua `develop`:** Antes de começar, garanta que sua branch `develop` local está atualizada com o repositório principal.
2.  **Crie uma `feature branch`:** Crie uma nova branch a partir da `develop`. O nome deve ser descritivo, prefixado com `feat/`:
    ```bash
    git checkout develop
    git pull origin develop
    git checkout -b feat/nome-da-sua-feature
    ```
3.  **Desenvolva e Faça Commits:** Trabalhe na sua feature e faça commits seguindo o nosso padrão de mensagens.
4.  **Abra um Pull Request (PR):** No GitHub, abra um Pull Request da sua `feature branch` para a branch `develop` do repositório principal para revisão.

## ✍️ Padrões de Commit e Pull Request

### Padrão de Commits
Todas as mensagens de commit **devem** seguir o padrão adaptado abaixo para manter um histórico limpo e legível.

A estrutura é: `(tipo) descrição curta em português brasileiro`

* **Importante:** Sempre que um agente de IA (como o Antigravity) criar o commit, a assinatura de co-autoria deve ser adicionada:
  `Co-authored-by: Antigravity AI <ai@antigravity.dev>`

#### Guia Rápido de Tipos de Commit

| Tipo | Quando Usar | Exemplo |
| :--- | :--- | :--- |
| **`feat`** | Adicionar uma nova funcionalidade. | `(feat) implementar endpoint POST /v1/devocional` |
| **`fix`** | Corrigir um bug. | `(fix) corrigir validação de entrada no guardrail` |
| **`ci`** | Alterar os arquivos de workflow. | `(ci) adicionar job para deploy em produção` |
| **`docs`** | Mudar documentação ou ADRs. | `(docs) atualizar especificação de arquitetura hexagonal` |
| **`refactor`**| Melhorar o código sem mudar o que ele faz. | `(refactor) extrair chamada Gemini para um adaptador separado` |
| **`chore`** | Tarefas de manutenção (pom.xml, etc). | `(chore) atualizar versão do Spring Boot` |
| **`test`** | Adicionar ou corrigir testes. | `(test) adicionar testes unitários para geração de devocionais` |

### Padrão de Pull Requests (PRs)
- **Título do PR:** Deve ser uma frase clara e humana em português (ex: "Implementação do endpoint de devocional com integração Gemini"). Não use o prefixo `(feat)` no título do PR.
- **Descrição do PR:** Siga o template padrão injetado pelo GitHub detalhando o contexto, as mudanças e como testar.

> **Nota para Agentes de IA:** Leia o `.github/PULL_REQUEST_TEMPLATE.md` para entender como preencher a descrição do PR.

## 📐 Padrões de Código

* **Formatação e Qualidade:** O projeto utiliza ferramentas de análise estática (SpotBugs) para garantir a consistência e a qualidade do código.
* **Garantia:** Antes de enviar qualquer alteração, é mandatório que o build local passe sem erros executando `mvn clean verify`. O pipeline de CI irá validar esta condição.

---
*Este documento poderá ser atualizado a qualquer momento.*