# RB-[NNN]: [Título do Procedimento Operacional]

* **Status:** Ativo
* **Data:** AAAA-MM-DD
* **Update:** _(preencher se houver revisão)_
* **Ambiente:** Produção | Homologação | Local | Todos
* **Tempo Estimado:** [N] minutos
* **Relacionado:** _(ADR-NNN, US-NNN)_

---

## Objetivo

<!--
Descreva em 1-2 frases o que este runbook faz e quando deve ser utilizado.
-->

_[Descreva o objetivo e o gatilho de uso deste runbook.]_

---

## Pré-Requisitos

<!--
Liste tudo que o executor precisa ter antes de começar.
-->

- [ ] Acesso ao repositório `farol-da-fe-api` com permissão de [nível]
- [ ] [Ferramenta X] instalado e configurado
- [ ] Variáveis de ambiente necessárias:
  ```
  VARIAVEL_A=valor
  VARIAVEL_B=valor
  ```
- [ ] _(Outros pré-requisitos)_

---

## Procedimento

### Passo 1: [Nome do Passo]

<!--
Seja específico. Inclua o comando exato, o resultado esperado e o que fazer se falhar.
-->

```bash
# Descrição do que este comando faz
comando --flag valor
```

**Resultado esperado:** _[O que deve aparecer ou acontecer.]_

**Se falhar:** _[O que verificar ou fazer em caso de erro.]_

---

### Passo 2: [Nome do Passo]

```bash
comando --flag valor
```

**Resultado esperado:** _[...]_

---

### Passo 3: [Nome do Passo]

_[Descrição do passo se não for apenas um comando.]_

---

## Verificação Pós-Execução

<!--
Como confirmar que o procedimento foi executado com sucesso?
-->

- [ ] Verificar endpoint de saúde: `GET /actuator/health` → deve retornar `{ "status": "UP" }`
- [ ] Verificar logs: _[O que procurar nos logs para confirmar sucesso]_
- [ ] _(Outras verificações)_

---

## Rollback

<!--
Como desfazer este procedimento em caso de problema?
Se não houver rollback possível, explique por quê e quais são as alternativas.
-->

### Procedimento de Rollback

```bash
# Comando para reverter
comando-de-rollback
```

**Impacto do rollback:** _[O que é perdido ou afetado ao reverter.]_

---

## Troubleshooting

<!--
Erros conhecidos e como resolvê-los.
-->

### Erro: `[Mensagem de erro conhecida]`
**Causa:** _[O que provoca este erro.]_  
**Solução:** _[Como resolver.]_

### Erro: `[Outra mensagem de erro]`
**Causa:** _[...]_  
**Solução:** _[...]_

---

## Referências

* [Documentação relevante](URL)
* ADR-NNN: _[Decisão arquitetural relacionada]_
