# ADR-012: Proposta de Adoção do Newman para Validação de APIs

* **Status:** Proposto
* **Data:** 2026-08-11

## Contexto

O projeto já possui um contrato OpenAPI (`docs/api/openapi.yaml`) e a intenção de reforçar a validação do comportamento de API em um fluxo de desenvolvimento orientado por spec. A estratégia atual de testes ainda não define uma ferramenta padrão para execução de coleções de teste de API fora do ambiente Java, e o README não aponta um caminho claro para testes locais com contêiner e validação de endpoints.

Ao mesmo tempo, a equipe busca manter o custo baixo e a complexidade reduzida. Ferramentas como Newman (runner CLI para coleções Postman) são amplamente usadas para automação de testes de API, suportam execução em modo CI/CD e funcionam bem em ambientes de contêiner como Podman.

## Decisão

Adotar o Newman como ferramenta de execução de coleções de teste de API para validar o contrato e o comportamento dos endpoints.

A ferramenta será usada como um complemento à suíte de testes Java existente, não como substituto dos testes unitários/integrados do backend.

## Justificativa

1. **Baixo custo e simplicidade:** Newman é uma ferramenta gratuita, leve e de fácil instalação via npm, o que atende à meta de manter custo próximo de zero.
2. **Validação de contrato e fluxo:** Permite transformar cenários de API em coleções executáveis, reforçando o fluxo contract-first e servindo como documentação de teste para QA e CI.
3. **Compatibilidade com contêineres:** Newman pode ser executado nativamente em imagens baseadas em Node.js ou em ambientes de CI que suportem containers, facilitando testes locais com Podman e pipelines de GitHub Actions.
4. **Separação de camadas:** Mantém os testes de API alinhados com o contrato OpenAPI sem poluir o código de aplicação Java com casos de teste de integração de ponto de extremidade.

## Consequências

### Positivas
* Facilita a criação de um conjunto de testes API independente do código Java, útil para validação de regressão de contrato por meio de pipelines CI/CD.
* Dá um caminho claro para documentar testes manuais e automatizados no README e para executar verificações locais em contêiner.
* Ajuda a convencer revisores e agentes IA de que o projeto tem uma camada de validação de contrato além dos testes unitários.

### Negativas
* Introduz uma ferramenta adicional no repositório e uma dependência de ambiente `npm`/Node.js, ainda que apenas para testes.
* Requer disciplina para manter a coleção Newman sincronizada com o `openapi.yaml` e com os casos de uso reais.

## Declaração de Adoção

Esta proposta será aceita quando houver:

1. uma coleção de testes Newman inicializada e versionada no repositório;
2. um comando documentado no README para rodar os testes localmente com Podman ou npm;
3. um passo de pipeline de GitHub Actions que execute a coleção Newman como validação adicional de API.

## Observação

Caso a equipe decida que a sobrecarga de manter uma coleção Newman não compensa o benefício, a decisão poderá ser revisada e revertida para uma abordagem baseada exclusivamente em testes Java e validação de contrato via build do OpenAPI Generator.
