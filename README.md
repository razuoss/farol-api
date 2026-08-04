# Farol da Fé
## API para estudo bíblico exegético e devocional com IA

O **Farol da Fé** é um assistente que visa apoiar cristãos com conteúdo informativo de qualidade para entendimento e estudos dos textos bíblicos, auxiliando no cruzamento de dados históricos, culturais e textuais.

A motivação deste projeto nasceu da convergência entre duas jornadas do autor: sua especialização em engenharia de software moderna e sua jornada como estudante de teologia. Por isso, mesmo como um projeto experimental e autoral, a **Farol da Fé API** adota padrões de mercado de alta maturidade.
  
A motivação deste projeto nasceu da convergência entre duas jornadas do autor: sua especialização em engenharia de software moderna e sua jornada como estudante de teologia. Por isso, mesmo como um projeto experimental e autoral, a **Farol da Fé API** adota padrões de mercado de alta maturidade.
  
---

## Propósito  

O **Farol da Fé** é um assistente pensado para auxiliar cristãos no estudo aprofundado das Escrituras e aplicação devocional, na organização de ideias, correlações teológicas e contexto histórico, buscando estar mais próximo possível da fidelidade textual e melhorar interpretações superficiais ou distorcidas. Para isso, o sistema utiliza Inteligência Artificial Generativa com instruções criteriosas para uma maior assertividade teológica.
O **Farol da Fé** é um assistente pensado para auxiliar cristãos no estudo aprofundado das Escrituras e aplicação devocional, na organização de ideias, correlações teológicas e contexto histórico, buscando estar mais próximo possível da fidelidade textual e melhorar interpretações superficiais ou distorcidas. Para isso, o sistema utiliza Inteligência Artificial Generativa com instruções criteriosas para uma maior assertividade teológica.

O conteúdo gerado por este software destina-se **exclusivamente** ao apoio em estudos e devocionais com viés protestante histórico. **Não deve ser utilizado, sob nenhuma hipótese, como substituto do estudo pessoal da Bíblia, da oração e da direção do Espírito Santo, nem como conselho pastoral, jurídico, médico, psicológico ou normativo oficial de igrejas/denominações.**

---

## Como obter o projeto

Você pode obter este repositório de duas formas principais:

### 1. Clone direto

Se você apenas quiser explorar o projeto:

```bash
git clone https://github.com/razuoss/farol-api.git
cd farol-api
```


### 2. Fork (recomendado para contribuição)

Se você quiser estudar, adaptar ou contribuir com o projeto:

1. Acesse o repositório no GitHub.
2. Clique em "Fork".
3. Clone o seu fork localmente:

```bash
git clone https://github.com/<seu-usuario>/farol-api.git
cd farol-api
```

---

## Aviso Legal e Isenção de Responsabilidade

1. **Uso de IA e Possibilidade de Erros:** As respostas são processadas através de modelos de Inteligência Artificial Generativa. Modelos de IA estão sujeitos a imprecisões, por melhores que sejam as instruçẽos, bem como erros de interpretação ou criação de conteúdos divergentes. Caso ao usuário sempre validar todo o conteúdo recebido diretamente no texto bíblico e com lideranças pastorais idôneas.
2. **Caráter Auxiliar:** Este software é uma ferramenta assistente. Nenhuma resposta gerada pela API possui caráter doutrinário, normativo ou dogmático.
3. **Isenção de Garantia ("AS IS"):** O software é fornecido "no estado em que se encontra" (*AS IS*), sem garantias de qualquer tipo, expressas ou implícitas. O autor não se responsabiliza por usos indevidos e quaisquer danos, perdas ou decisões tomadas com base nas informações geradas por este sistema.
3. **Isenção de Garantia ("AS IS"):** O software é fornecido "no estado em que se encontra" (*AS IS*), sem garantias de qualquer tipo, expressas ou implícitas. O autor não se responsabiliza por usos indevidos e quaisquer danos, perdas ou decisões tomadas com base nas informações geradas por este sistema.
4. **Uso por Terceiros:** A replicação, execução ou hospedagem deste código por terceiros é de inteira responsabilidade de quem o fizer, devendo respeitar integralmente os termos da licença do projeto.


---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- Docker

---

## 📂 Estrutura do Projeto

A aplicação adota estritamente a **Arquitetura Hexagonal** em um modelo de **microserviço único**:

```
farol-da-fe-api/
 ├── docs/                           
 │    ├── adr/                       # Registros de Decisão Arquitetural 
 │    ├── api/                       # Registros de Contrato de API           
 │    ├── features/                  # Registros de Funcionalidades          
 │    ├── product.md                 # Documento com detalhamento do Produto/Plataforma
 │    └── architecture.md            # Documento com detalhamento de arquitetura
 ├── src/
 │    ├── main/
 │    │    ├── java/.../farol_api/
 │    │    │    ├── domain/          
 │    │    │    │    ├── model/      # Entidades de domínio puras, Value Objects e exceções
 │    │    │    │    ├── port/       # Contratos de entrada e saída (Inbound/Outbound Ports)
 │    │    │    │    │    ├── in/    # Use Cases 
 │    │    │    │    │    └── out/   # Interfaces de saída 
 │    │    │    │    └── service/    # Implementações puras das regras de negócio
 │    │    │    └── infra/  
 │    │    │         ├── adapter/
 │    │    │         │    ├── in/web/    # Controllers REST, Webhook do Telegram e DTOs (records)
 │    │    │         │    └── out/
 │    │    │         │         ├── gemini/ # GeminiAiAdapter implementando GenAiPort
 │    │    │         │         └── audit/  # GoogleSheetsAdapter implementando AuditRepositoryPort
 │    │    │         ├── config/         # Configurações Spring, Virtual Threads, CORS, Beans
 │    │    │         └── guardrail/      # Filtros anti-injection, sanitização e limites (max 1000 chars)
 │    │    └── resources/
 │    │         ├── prompts/         # Prompts de sistema executáveis
 │    │         └── application.yml  # Configurações de ambiente do Spring Boot
 │    └── test/                      
 ├── AGENTS.md                       # Diretrizes arquiteturais para Agentes IA
 ├── skills.md                       # Alias de referência rápida para AGENTS.md
 ├── CODE_OF_CONDUCT.md              # Descrição do Código de Conduta               
 ├── CONTRIBUTING.md                 # Descrição do Guia de Contribuições
 ├── Dockerfile                      # COnfigurações de provisionamento de container
 ├── LICENSE.md                      # Descrição da Licença de uso e cópia
 ├── pom.xml                         # Configurações gerais do projeto
 └── TERMS.md                        # Descrição dos Termos de uso
```

---

## 📜 Licença

Este projeto está licenciado sob a licença **Creative Commons Atribuição-NãoComercial-CompartilhaIgual 4.0 Internacional (CC BY-NC-SA 4.0)**.  

Você pode compartilhar e adaptar o material para fins não comerciais, desde que atribua o crédito ao projeto original e distribua sob a mesma licença. Veja detalhes em [LICENCE.md](LICENCE.md) e [TERMS.md](TERMS.md).