# Especificação: Devocional (US-001)

Esta especificação define os critérios de aceite e o comportamento esperado do núcleo de geração de devocionais (US-001).
Ela serve como documentação viva, seguindo o padrão de Spec-Driven Development e TDD (Test-Driven Development).

## 1. Visão Geral
O fluxo de Devocional orquestra o recebimento de uma solicitação textual do usuário e delega a um provedor de Inteligência Artificial Generativa para retornar uma resposta estruturada de reflexão bíblica. Na US-001, a IA é mockada.

## 2. Critérios de Aceite (Gherkin)

### Cenário 1: Fluxo Completo com Sucesso
**Dado** que um cliente fornece um tema válido (ex: "mensagem de esperança")
**Quando** o caso de uso `DevocionalUseCase` é acionado
**E** ele invoca a interface `GenAiPort`
**Então** o sistema deve retornar um `Devocional` estruturado contendo:
- Titulo
- Texto Chave
- Contexto Histórico
- Análise do Texto
- Aplicação Prática
- Oração
- Aviso Pastoral

### Cenário 2: Requisição Inválida (Validação de Entrada)
**Dado** que um cliente fornece um tema nulo, vazio ou composto só de espaços
**Quando** a solicitação é validada
**Então** a aplicação deve recusar a entrada antes de chegar ao domínio
**E** lançar erro de validação (resultando em HTTP 400 Bad Request)

## 3. Comportamento do Mock (US-001)
Nesta versão, a implementação `MockGenAiAdapter` de `GenAiPort` deve:
- Retornar sempre a mesma estrutura estática de resposta independentemente do tema recebido.
- O mock servirá para validar a integração fim-a-fim da arquitetura hexagonal.
