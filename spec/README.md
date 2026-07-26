# 📐 Pasta `spec` — Templates e Especificações

Esta pasta contém os templates padrão para todos os tipos de documentos do projeto **Farol da Fé API**.

O objetivo é garantir consistência, rastreabilidade e qualidade na documentação,
independentemente do momento em que o documento for criado.

---

## 📂 Templates Disponíveis

| Arquivo | Tipo | Uso |
|---|---|---|
| [`TEMPLATE-ADR.md`](./TEMPLATE-ADR.md) | Architecture Decision Record | Registrar decisões arquiteturais |
| [`TEMPLATE-US.md`](./TEMPLATE-US.md) | User Story | Definir histórias de usuário para o backlog |
| [`TEMPLATE-RFC.md`](./TEMPLATE-RFC.md) | Request for Comments | Propor e discutir mudanças de maior impacto |
| [`TEMPLATE-RUNBOOK.md`](./TEMPLATE-RUNBOOK.md) | Runbook Operacional | Guias de operação, deploy e troubleshooting |
| [`TEMPLATE-CONTRACT.md`](./TEMPLATE-CONTRACT.md) | Contrato de API | Especificar endpoints REST |

---

## 📏 Convenções de Nomenclatura

### ADRs
```
docs/adr/ADR[NNN]-[descricao-curta-com-hifens].md
```
Exemplo: `ADR012-estrategia-de-autenticacao.md`

### User Stories
```
docs/us/US[NNN]-[descricao-curta-com-hifens].md
```
Exemplo: `US007-endpoint-devocional.md`

### RFCs
```
docs/rfc/RFC[NNN]-[descricao-curta-com-hifens].md
```
Exemplo: `RFC001-app-anotacoes-biblicas.md`

### Runbooks
```
docs/runbooks/RB[NNN]-[descricao-curta-com-hifens].md
```
Exemplo: `RB001-deploy-cloud-run.md`

---

## 🔄 Ciclo de Vida dos Documentos

```
Rascunho → Proposto → Em Revisão → Aceito → Obsoleto
```

| Status | Significado |
|---|---|
| `Rascunho` | Documento incompleto, ainda sendo escrito |
| `Proposto` | Completo, aguardando discussão ou aprovação |
| `Em Revisão` | Em processo de revisão com alterações em aberto |
| `Aceito` | Decisão ou spec aprovada e em vigor |
| `Obsoleto` | Substituído por outro documento (referenciar o sucessor) |
| `Rejeitado` | Avaliado e não aprovado (manter registro da razão) |

---

## 🔗 Rastreabilidade

Todo documento deve referenciar os demais relacionados.
Use o padrão `(ADR-NNN)`, `(US-NNN)` ou `(RFC-NNN)` inline no texto.

Exemplo:
> A decisão de usar MongoDB (ADR-009) decorre da limitação de cotas do Google Sheets
> identificada durante a implementação da US-04.
