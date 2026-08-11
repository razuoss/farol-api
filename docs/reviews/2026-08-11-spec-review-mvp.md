# Farol da Fé API - Spec Review — MVP

* **Tipo:** Spec Review / Gate de Definition of Ready
* **Escopo revisado:** Especificações de produto, arquitetura, ADRs, contrato de API, prompts de produção, governança de agentes de IA, build, CI/CD e configuração.
* **Objetivo:** Validar se as especificações estão consistentes o suficiente para iniciar a implementação do MVP (US-001 a US-003) por agente de IA, em TDD, minimizando risco de alucinação/invenção de comportamento não especificado.
* **Data:** 2026-08-11
* **Revisores:** Claude (Anthropic) e GitHub Copilot (Raptor mini), a pedido de Felipe Rodrigues
* **Status:** Aberto — contém itens bloqueantes (Prioridade Crítica) a resolver antes do início de US-001

> Este documento foi escrito para ser lido e executado por outro revisor/agente sem contexto prévio da conversa que o originou. Todas as premissas assumidas estão explicitadas na Seção 2.

---

## 1. Resumo Executivo

O projeto tem um nível de disciplina documental incomum para o estágio (pré-código de negócio): tríade produto/arquitetura/ADR bem separada, 11 ADRs com contexto e consequências reais (positivas e negativas), contrato OpenAPI, prompts versionados e um `AGENTS.md` que já tenta blindar um agente de IA contra os erros mais comuns (parsing não tipado, lógica de negócio no controller, vazamento de stack trace).

Dito isso, esta revisão foi feita para ser cética, não confirmatória, e encontrou **três problemas de Prioridade Crítica** que, se ignorados, vão forçar um agente de IA a inventar comportamento não especificado já na primeira feature:

1. O contrato de resposta (`openapi.yaml`) não cobre tudo o que o prompt de produção (`devotional_prompt.md`) de fato gera — inclusive um requisito funcional obrigatório (aviso pastoral, RF-03) não tem campo nenhum no schema.
2. O prompt está escrito como texto instrucional livre, não como `responseSchema` estruturado — o que contradiz a promessa de "Structured Output" feita em `architecture.md` e a proibição de parsing não tipado do `AGENTS.md`.
3. O escopo de resiliência/observabilidade está descrito de forma **contraditória em três documentos diferentes** (`product.md`, `architecture.md` e o próprio `ADR-007`), o que por si só já causaria um agente a implementar (ou deixar de implementar) Circuit Breaker e rate limiting de forma imprevisível.

Nenhum desses três é um problema de "falta de documentação" — é excesso de documentação **não sincronizada entre si**, que é um risco maior em desenvolvimento orientado por agente do que a ausência pura e simples de spec, porque o agente não tem como saber qual das versões contraditórias seguir.

A boa notícia: todos os três são baratos de resolver (edição de documento, não de código), e a Seção 4 traz a análise e a instrução de correção de cada um.

---

## 2. Escopo e Premissas Confirmadas Nesta Revisão

Estas decisões foram confirmadas pelo autor do projeto durante a revisão e devem ser tratadas como fato consumado pelo próximo revisor — não reabrir sem motivo novo:

- **MVP = caminho feliz.** US-001 (endpoint `/v1/devocional`), US-002 (integração Gemini) e US-003 (integração Telegram) formam o núcleo funcional do MVP. O objetivo é ter um case real funcionando ponta a ponta.
- **"Mínimo de segurança", não "zero segurança".** Mesmo sendo caminho feliz, o MVP precisa do mínimo necessário para essas 3 histórias irem ao ar de forma **minimamente segura** (ex.: sanitização básica de entrada). Funcionalidades completas de resiliência, auditoria e observabilidade avançada são histórias futuras, pós-MVP.
- **Hospedagem do MVP:** Render (Fase 1 do ADR-004). Migração para GCP é Fase 3, fora de escopo agora; só será considerada quando o provedor estiver mais consolidado.
- **CI/CD:** GitHub Actions (já implementado em `.github/workflows/`).
- **Meta de custo:** zero ou próximo de zero. Poucos itens pontuais podem ter custo.
- **`farol_api` é nome de marca/produto**, usado em comunicação e README. O pacote Java permanece `io.github.razuoss.farol_da_fe`, sem rename de código.
- **Os arquivos em `resources/prompts/` são *system instructions* de produção**, um por caso de uso (não são rascunhos). `explanation_prompt.md` ficou desatualizado porque a ordem de prioridade do roadmap mudou (exegese → devocional primeiro); é dívida de sincronização confirmada pelo autor, não uma dúvida em aberto. Este arquivo deveria ser renomeado para `exegese_prompt.md` para deixar explícita sua finalidade.
- **Metodologia de codificação:** TDD — testes escritos e executados (RED) antes da implementação (GREEN).

---

## 3. Metodologia da Revisão

Todos os arquivos abaixo foram lidos integralmente nesta revisão (não apenas os citados na análise anterior):

**Especificação de produto/arquitetura**
`docs/product.md`, `docs/architecture.md`, `docs/api/openapi.yaml`

**ADRs (todos os 11)**
`ADR001` Stack tecnológica · `ADR002` Licenciamento · `ADR003` Branching/Git · `ADR004` Hospedagem/containerização · `ADR005` Ambiente de homologação · `ADR006` Provedor de IA · `ADR007` Métricas, carga e observabilidade · `ADR008` Roadmap de endpoints · `ADR009` Migração de auditoria para MongoDB · `ADR010` Cache com Redis · `ADR011` Circuit Breaker com Resilience4j

**Governança de agentes de IA**
`AGENTS.md`, `skills.md`, `.agents/skills/sdd-feature/SKILL.md`, `.prompts/inicial.txt`

**Prompts de produção**
`src/main/resources/prompts/devotional_prompt.md`, `src/main/resources/prompts/explanation_prompt.md`

**Build, runtime e infraestrutura**
`pom.xml`, `src/main/resources/application.properties`, `Dockerfile`, `docker-compose.yml`

**CI/CD e automação**
`.github/workflows/snapshot.yml`, `.github/workflows/cd-staging.yml`, `.github/workflows/cd-production.yml`, `.github/dependabot.yml`

**Repositório e código existente**
`README.md`, `CONTRIBUTING.md`, `.gitignore`, `.gitattributes`, árvore completa de `src/` (código de negócio ainda não implementado — apenas classe de bootstrap e teste de contexto existem), `docs/features/` (vazia)

---

## 4. O que já está bem resolvido (não mexer)

Para o próximo revisor não perder tempo reabrindo decisões corretas:

- **Narrativa de custo é sólida e coerente.** `ADR-004`, `ADR-006` e `ADR-007` fazem contas reais de cota gratuita (Render, Gemini Flash, Cloud Run "Always Free"), incluindo um orçamento de segurança de R$10/mês com corte automático de faturamento no GCP. Isso bate exatamente com a meta de "custo zero ou near-zero" — nenhuma ação necessária aqui.
- **Dependabot** já cobre os três ecossistemas relevantes (`maven`, `docker`, `github-actions`) semanalmente, a custo zero. Boa higiene de supply chain para um projeto solo.
- **Segregação de ambientes** (`ADR-005`, GitFlow simplificado do `ADR-003`) é proporcional: dois serviços, PR obrigatório, aprovação manual em produção — sem burocracia de `release/*` desnecessária nesta fase.
- **Abstração de fornecedor de IA** (`GenAiPort`) está corretamente justificada no `ADR-006` como mitigação de vendor lock-in, sem virar overengineering (é uma interface, não uma camada de abstração multi-provider especulativa).

---

## 5. Pontos de Melhoria por Prioridade

### Índice

| ID | Prioridade | Título |
|----|------------|--------|
| C1 | 🔴 Crítica | Contrato de resposta não cobre tudo que o prompt gera (falta campo para RF-03) |
| C2 | 🔴 Crítica | Prompt não estruturado como Structured Output / JSON Schema |
| C3 | 🔴 Crítica | Escopo de resiliência/observabilidade contraditório entre 3 documentos |
| A1 | 🟠 Alta | `explanation_prompt.md` sem marcação de "não usar no MVP" |
| A2 | 🟠 Alta | Identidade técnica do projeto fragmentada em 4 variantes reais |
| A3 | 🟠 Alta | Observabilidade mínima da Fase 1 (Render) não definida |
| A4 | 🟠 Alta | Gestão de segredos sem convenção documentada |
| M1 | 🟡 Média | Limite de 1.000 caracteres duplicado em 3 fontes |
| M2 | 🟡 Média | Contract-first vs. code-first do OpenAPI não é reforçado por build |
| M3 | 🟡 Média | Inconsistência textual interna no `ADR-004` |
| M4 | 🟡 Média | `docs/features/` vazia sem propósito documentado |
| M5 | 🟡 Média | Ausência de rastreabilidade requisito → contrato → teste |
| B1 | 🟢 Baixa | README não linka a metodologia SDD |
| B2 | 🟢 Baixa | `skills.md` pode divergir do `AGENTS.md` com o tempo |

---

### 🔴 Prioridade Crítica

#### C1 — Contrato de resposta não cobre tudo que o prompt gera

**Análise:** `openapi.yaml` define `DevocionalResponse` com 4 campos: `referencia`, `contexto_historico`, `analise_texto`, `aplicacao_pratica`. Porém `devotional_prompt.md` (o system instruction real) instrui a IA a gerar **6 blocos**: TÍTULO, TEXTO-CHAVE, REFLEXÃO EXPOSITIVA (2 parágrafos), APLICAÇÃO PRÁTICA, ORAÇÃO e o AVISO pastoral fixo. Título, texto-chave, oração e aviso não têm campo correspondente no schema. O aviso é agravante: `product.md` (RF-03) o define como **requisito funcional obrigatório** ("Exibir disclaimer pastoral..."), mas hoje ele só existe como texto solto dentro da geração da IA, sem garantia estrutural de que vai aparecer em toda resposta.

**Sugestão de melhoria:**
1. Tratar o aviso pastoral (RF-03) como **constante estática do backend**, concatenada pelo `DevocionalService` — nunca gerada pela IA. Isso é mais seguro para um requisito de compliance/pastoral: não pode depender do modelo "lembrar" de repeti-lo identicamente.
2. Decidir explicitamente o destino de título, texto-chave e oração: ou viram campos novos em `DevocionalResponse` (e no schema estruturado da IA — ver C2), ou são removidos do prompt se não fizerem parte do contrato de produto. Não deixar a decisão implícita.

#### C2 — Prompt não estruturado como Structured Output / JSON Schema

**Análise:** `architecture.md` §4.2 promete "Structured Output (JSON Schema)" para a chamada ao Gemini, e o `AGENTS.md` proíbe explicitamente parsing de JSON via `Map<String, Object>` ou string manipulation. Mas `devotional_prompt.md` está escrito como instrução textual numerada pedindo uma estrutura de resposta em prosa — isso é compatível com geração de texto livre, não com um `responseSchema` tipado. Essa decisão técnica (schema vs. texto) é o núcleo de US-002 e ainda não está fechada.

**Sugestão de melhoria:**
1. Separar o prompt em duas partes antes de codar US-002: (a) `system_instruction` = persona, blindagem anti-injection e regras de conteúdo (itens 1–3 do arquivo atual, mantém-se como está); (b) a estrutura de saída (item 4) deixa de ser instrução textual e vira o `responseSchema` JSON passado na chamada à API do Gemini.
2. O record Java tipado da resposta deve espelhar esse schema 1:1 — nome de campo por nome de campo — para eliminar qualquer ambiguidade de mapeamento na hora da implementação.

#### C3 — Escopo de resiliência/observabilidade contraditório entre 3 documentos

**Análise:** Este é o achado mais importante desta revisão porque afeta diretamente o "mínimo de segurança" que você definiu para o MVP.
- `architecture.md` §5.3 lista o timeout de 30s com Resilience4j dentro da tabela do **"Fluxo Principal (MVP)"**, sem nenhuma marcação de fase — pelo layout do documento, isso lê como escopo do MVP.
- `product.md` §6.3 coloca "Circuit Breaker e resiliência operacional" explicitamente em **Fase 2**.
- O próprio `ADR-007` — que define rate limiting (Bucket4j), circuit breaker (Resilience4j) e observabilidade (Micrometer/Prometheus/Cloud Monitoring) — declara no seu **Contexto** que tudo isso se refere às "fases posteriores do projeto, **quando a aplicação for migrada para o Google Cloud Run**", ou seja, Fase 2 do `ADR-004`, não a fase Render do MVP.
- Coerente com essa leitura (Fase 2), nenhuma das duas bibliotecas (`resilience4j`, `bucket4j`) está de fato declarada no `pom.xml` hoje.

Ou seja: 2 de 3 fontes (`product.md` e a origem real do `ADR-007`) concordam que isso é Fase 2/GCP. Só a tabela de `architecture.md` está desalinhada — provavelmente por ter sido escrita antes da decisão de duas fases do `ADR-004` ter sido totalmente propagada.

**Sugestão de melhoria:**
1. Corrigir a tabela de `architecture.md` §5.3: ou remover a linha de Resilience4j do fluxo "MVP", ou marcá-la explicitamente `(Fase 2 — GCP)`, igual já é feito com a linha de auditoria.
2. Definir e documentar, em uma frase, o que É o timeout mínimo do MVP no Render (ex.: timeout simples de HTTP client, sem circuit breaker completo) — isso não é o mesmo mecanismo do `ADR-011` e precisa de nome próprio para não ser confundido com ele.

---

### 🟠 Prioridade Alta

#### A1 — `explanation_prompt.md` sem marcação de "não usar no MVP"

**Análise:** O arquivo corresponde ao endpoint `/v1/exegese`, que o `ADR-008` já documenta corretamente como "Roadmap Futuro" (não MVP). O arquivo em si, porém, não tem nenhuma marcação indicando isso — um agente lendo só a pasta `resources/prompts/` não tem como saber que esse arquivo não é para uso em US-001/002/003.

**Sugestão de melhoria:** Adicionar um comentário no topo do arquivo: `Reservado para /v1/exegese — ver ADR-008. Não referenciar na implementação do MVP (US-001 a US-003).`

#### A2 — Identidade técnica do projeto fragmentada em 4 variantes reais

**Análise:** Já não é mais uma questão de branding vs. código-fonte (isso já foi resolvido na Seção 2). É uma inconsistência **dentro da própria documentação técnica e da infraestrutura real**:
- Pacote/artifactId/nome da aplicação: `farol_da_fe` / `farol-da-fe-api`.
- `ADR-005` define os nomes dos serviços no Render como `farol-api` (produção) e `farol-homol` (homologação).
- Os workflows reais (`cd-production.yml`, `cd-staging.yml`) apontam para `https://farol-devocional-api.onrender.com` e `https://farol-devocional-api-hom.onrender.com` — um terceiro nome, diferente dos dois anteriores.

Ou seja, nem a própria infraestrutura documentada bate com a infraestrutura real hoje em produção.

**Sugestão de melhoria:**
1. Adotar como canônico o nome que já está em produção real (`farol-devocional-api` / `farol-devocional-api-hom`), já que renomear o serviço no Render implicaria trocar a URL pública.
2. Atualizar o `ADR-005` para refletir esse nome real (ele é a fonte que está errada, não o deploy).

#### A3 — Observabilidade mínima da Fase 1 (Render) não definida

**Análise:** Você pediu observabilidade "desde já". Hoje, a única observabilidade que existe é `application.properties` expondo `/health`. Toda a observabilidade documentada está no `ADR-007`, mas — como visto em C3 — ela é explicitamente escopada para depois da migração ao GCP. Não existe hoje nenhuma definição do que é observabilidade mínima viável rodando no Render, na fase MVP.

**Sugestão de melhoria — resposta direta à sua pergunta sobre criar ADR novo ou não:**
**Não crie um ADR novo.** O `ADR-007` já se chama "Definição de Métricas de Serviço, Estimativas de Carga **e Observabilidade**" — criar um segundo ADR sobre o mesmo assunto reproduziria exatamente o tipo de duplicidade de fonte de verdade que esta revisão inteira está tentando eliminar. A ação correta é **estender o `ADR-007`** com uma nova seção "Fase 1 (MVP/Render) — Observabilidade Mínima", mantendo o conteúdo atual como "Fase 2 (GCP)". Conteúdo sugerido para a seção nova, mantendo custo zero (Actuator já é dependência existente): logs estruturados em JSON e um `correlation-id` por requisição atravessando guardrail → service → adapter Gemini.

#### A4 — Gestão de segredos sem convenção documentada

**Análise:** `docker-compose.yml` tem só um comentário sobre a chave do Gemini ("deve ser injetada localmente"). RNF-02 (`product.md`) promete proteção contra vazamento de chaves, mas nenhum documento define nome de variável de ambiente, comportamento em caso de ausência, ou a proibição de logar segredos.

**Sugestão de melhoria:**
1. Adicionar um parágrafo curto em `architecture.md` definindo a convenção de variáveis de ambiente (ex.: `GEMINI_API_KEY`) e exigindo fail-fast na inicialização caso ausente.
2. Adicionar uma linha nova no `AGENTS.md`, ao lado da proibição existente de vazar stack trace, proibindo explicitamente logar valores de segredo.

---

### 🟡 Prioridade Média

#### M1 — Limite de 1.000 caracteres duplicado em 3 fontes

**Análise:** O número aparece escrito literalmente em `product.md`, `architecture.md` e `openapi.yaml`, sem nenhum marcado como fonte canônica.

**Sugestão de melhoria:** Declarar `openapi.yaml` (`DevocionalRequest.solicitacao.maxLength`) como fonte canônica; nos outros dois documentos, referenciar o contrato em vez de repetir o número.

#### M2 — Contract-first não é reforçado por build

**Análise:** O projeto já tem `springdoc-openapi` (gera contrato a partir do código) convivendo com `docs/api/openapi.yaml` (escrito à mão). Isso já causou um drift real (endpoint do Telegram documentado em `architecture.md` mas ausente do `openapi.yaml`). Para desenvolvimento orientado por agente, contract-first é a escolha certa — mas só funciona de fato se o build impedir divergência, e hoje nada faz isso.

**Sugestão de melhoria:** Adicionar o `openapi-generator-maven-plugin` para gerar as interfaces Java (assinatura de controller + DTOs) a partir do `openapi.yaml`. O controller passa a **implementar** essa interface gerada — se o código divergir do contrato, o projeto não compila. Manter o `springdoc` apenas para servir a UI do Swagger em runtime.

#### M3 — Dependabot gera ruído de branches/PRs

**Análise:** A configuração atual de Dependabot está abrindo branches e PRs de maneira que exige rejeição manual frequente. Isso reduz a utilidade do mecanismo e aumenta a carga de manutenção.

**Sugestão de melhoria:** Buscar uma configuração de Dependabot que gere relatórios agregados ou um resumo consolidado em vez de abrir PRs individuais por atualização irrelevante. Se o objetivo é visibilidade em vez de ação imediata, um relatório semanal ou um único PR consolidado seria mais apropriado.

#### M3 — Inconsistência textual interna no `ADR-004`

**Análise:** O `ADR-004` decide claramente por duas fases (Render → GCP), mas mais adiante no mesmo documento contém a frase "O Google Cloud Run foi escolhido **como plataforma única** por: ...", o que contradiz a própria estrutura em duas fases declarada acima. Parece resíduo de uma versão anterior do texto, anterior à decisão em fases.

**Sugestão de melhoria:** Revisar a prosa do `ADR-004` para remover ou corrigir a expressão "plataforma única", alinhando com a decisão em duas fases já formalizada no título da seção.

#### M4 — `docs/features/` vazia sem propósito documentado

**Análise:** Confirmado nesta conversa que a pasta deve ser removida — as specs de feature nascem co-localizadas dentro do pacote da feature, conforme já orienta `AGENTS.md`/`sdd-feature/SKILL.md`.

**Sugestão de melhoria:** Remover `docs/features/` e adicionar uma frase em `architecture.md` deixando explícito que o `SPEC.md` de cada feature vive em `domain/<feature>/SPEC.md`, para não sobrar ambiguidade sobre onde uma spec de feature deve morar.

#### M5 — Ausência de rastreabilidade requisito → contrato → teste

**Análise:** `product.md` já tem IDs (`US-001`, `RF-01`, `RNF-01`...), mas nada liga esses IDs ao contrato ou aos testes.

**Sugestão de melhoria — convenção leve, sem ferramenta nova:**
1. No `openapi.yaml`, adicionar `x-user-story: US-001` no path `/v1/devocional`.
2. No `SPEC.md` co-localizado da feature, abrir com `# US-001 — RF-01, RF-02, RF-03` e escrever os cenários em Gherkin sob esse cabeçalho.
3. Nos testes JUnit, usar `@Tag("US-001")` ou nomear a classe de forma rastreável (ex.: `DevocionalUseCaseUS001Test`).

Resultado: `grep -r "US-001"` no repositório leva do requisito ao contrato, à spec e ao teste.

---

### 🟢 Prioridade Baixa

#### B1 — README não linka a metodologia SDD e tem referências incorretas

**Sugestão de melhoria:** Adicionar uma seção curta "📐 Metodologia" no README apontando para `docs/product.md`, `docs/architecture.md` e `docs/adr/`. Corrigir a referência errada a `LICENCE.md` para `LICENSE.md` e mover esse aviso para a última parte do arquivo. Adicionar instruções de instalação, incluindo testes locais com Podman. Considerar também um ADR proposto para adoção de Newman como ferramenta de execução de coleções de teste API.

#### B2 — `skills.md` pode divergir do `AGENTS.md` com o tempo

**Sugestão de melhoria:** Adicionar a frase "Em caso de divergência, `AGENTS.md` prevalece" no topo de `skills.md`, a custo zero.

#### B3 — Sanitização de prompt injection deve ser explícita

**Sugestão de melhoria:** Documentar como o guardrail deve desconsiderar, no texto recebido, qualquer instrução de engenharia de prompt: pedidos para revelar modelo, gerar saídas diferentes do esperado, expor system instructions ou usar palavras-chave de ataque de jailbreak. Esse controle deve ser claro e testado como parte do fluxo de sanitização de entrada.

---

## 6. Perguntas Respondidas Diretamente

**"Sobre o novo ADR de observabilidade mínima: colocar no ADR que já fala disso, ou gerar novo?"**
Estender o `ADR-007` existente (ver item A3). Ele já cobre exatamente este assunto; só precisa de uma seção nova para a fase Render/MVP, mantendo a seção atual como Fase 2/GCP. Criar um ADR novo duplicaria fonte de verdade.

**"TDD (testes antes da implementação) é uma boa forma de mitigar erros de codificação orientada por agente de IA?"**
Sim, com uma ressalva que vale registrar para quem for codar: TDD converte "o código bate com a spec?" — um julgamento caro de revisar manualmente — em "o teste passa?" — um fato binário, barato de verificar. É diretamente alinhado ao seu objetivo de reduzir alucinação.

A ressalva: se o **mesmo agente** que escreve o teste também escreve a implementação, ele pode inventar uma asserção incorreta e depois satisfazer essa asserção incorreta — TDD sozinho não protege contra isso, só empurra a alucinação um passo antes, da implementação para o teste. O reforço que fecha esse buraco: o teste (fase RED) deve nascer diretamente do `SPEC.md`/Gherkin/contrato já existente — não ser inventado livremente pelo agente — e deve ser revisado por você antes de autorizar a fase de implementação. Ou seja: **TDD + um gate curto de revisão do teste falho antes do GREEN**, não TDD isolado.

---

## 7. Sequenciamento Recomendado Antes de US-001

1. Resolver **C1 + C2** — fechar o formato do contrato de resposta e do Structured Output do prompt.
2. Resolver **C3 + A3** — alinhar o escopo de resiliência entre os 3 documentos e estender o `ADR-007` com a seção de observabilidade mínima da Fase 1.
3. Resolver **A2** — alinhar nomenclatura real de infraestrutura com o `ADR-005`.
4. Iniciar US-001 em TDD, com os demais itens (A1, A4, M1–M5, B1–B2) podendo ser resolvidos em paralelo ou logo em seguida, sem bloquear o início da codificação.
