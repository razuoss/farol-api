# US-[NNN]: [Título da História]

<!--
  INSTRUÇÕES DE USO (apague este bloco antes de publicar):

  Este template reflete o padrão adotado no projeto Farol da Fé API.
  Preencha os campos do sidebar do GitHub Projects:
    - Priority: Critical | High | Medium | Low
    - Size: XS | S | M | L | XL
    - Estimate: número de pontos
    - Iteration: selecione a iteração correspondente

  Apague as seções que não se aplicam (ex: se não há riscos, remova a seção).
-->

Como um [tipo de cliente / persona — ex: sistema cliente, bot do Telegram, usuário final],
eu quero [ação ou funcionalidade desejada],
para que [benefício ou valor de negócio obtido].

---

**Regras de Negócio:**

R01. [Descreva a primeira regra de negócio de forma clara e objetiva.]
R02. [Descreva a segunda regra — foque no "o quê", não no "como".]
R03. [Continue numerando conforme necessário.]

---

**Critérios de Aceite:**

Cenário 1: [Nome do Cenário — ex: Fluxo Completo com Sucesso]

- Dado que [pré-condição do sistema ou do usuário].
- Quando [ação executada pelo cliente ou pelo sistema].
- Então [resultado esperado — resposta HTTP, comportamento, estado].

Cenário 2: [Nome do Cenário — ex: Requisição Inválida]

- Dado que [condição de entrada inválida — ex: campo nulo ou vazio].
- Quando [ação executada].
- Então [resposta de erro esperada — ex: status 400 Bad Request com mensagem clara].

Cenário 3: [Nome do Cenário — ex: Falha Interna / Serviço Indisponível]

- Dado que [condição de falha — ex: provedor de IA lança exceção].
- Quando [ação executada].
- Então [resposta esperada — ex: status 503 com mensagem amigável].

---

**Riscos e Dependências:**

- Risco: [Descreva o risco arquitetural ou técnico mais relevante desta história.]
- Dependência: [Esta story depende de outra? Ex: "Este card depende da conclusão da US-02, pois o XService precisa invocar o YAdapter."]

---

**Regras Técnicas:**

- [Onde deve ser implementado — ex: "O endpoint deve ser implementado no XController e a lógica de negócio no XService."]
- [Validação — ex: "A validação estrutural do corpo da requisição deve ser feita utilizando Bean Validation (@Valid, @NotBlank, etc.)."]
- [Testes — ex: "Os testes de integração devem ser feitos com @SpringBootTest e MockMvc, utilizando @MockBean para simular o comportamento do adapter e isolar o teste à lógica interna da API."]
- [Cobertura — ex: "A cobertura de testes na classe XService deve ser mantida acima de 85%."]
- [Regra arquitetural — ex: "A camada de Application/Domain não deve conhecer detalhes de framework web (@RestController) ou da infraestrutura externa."]

---

**Checklist de Entrega:**

- [ ] [Task 1 — ex: Criar a estrutura inicial de pacotes (domain, application, infrastructure).]
- [ ] [Task 2 — ex: Criar o DTO de entrada com anotações de validação e o DTO de saída.]
- [ ] [Task 3 — ex: Criar a interface (Porta de Saída) XPort.]
- [ ] [Task 4 — ex: Implementar o adapter XAdapter.]
- [ ] [Task 5 — ex: Implementar a classe de serviço XUseCase injetando a porta.]
- [ ] [Task 6 — ex: Implementar o XController mapeado para /v1/recurso.]
- [ ] [Task 7 — ex: Criar testes de unidade para a classe XUseCase.]
- [ ] [Task 8 — ex: Criar testes de integração com MockMvc para o XController.]
