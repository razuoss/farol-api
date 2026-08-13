# Farol da Fé

**API para estudo bíblico exegético e devocional com IA**

[![Java 21](https://img.shields.io/badge/Java-21-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License: CC BY-NC-SA 4.0](https://img.shields.io/badge/License-CC_BY--NC--SA_4.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-sa/4.0/)

O **Farol da Fé** é um assistente que visa apoiar cristãos com conteúdo informativo de qualidade para entendimento e estudos dos textos bíblicos, auxiliando no cruzamento de dados históricos, culturais e textuais.  

A motivação deste projeto nasceu da convergência entre duas jornadas do autor: sua especialização em engenharia de software moderna e sua jornada como estudante de teologia. Por isso, mesmo como um projeto experimental e autoral, a **Farol da Fé API** adota padrões de mercado de alta maturidade.



## Propósito  

O **Farol da Fé** é um assistente pensado para auxiliar cristãos no estudo aprofundado das Escrituras e aplicação devocional, na organização de ideias, correlações teológicas e contexto histórico, buscando estar mais próximo possível da fidelidade textual e melhorar interpretações superficiais ou distorcidas. Para isso, o sistema utiliza Inteligência Artificial Generativa com instruções criteriosas para uma maior assertividade teológica.

O conteúdo gerado por este software destina-se **exclusivamente** ao apoio em estudos e devocionais com viés protestante histórico. **Não deve ser utilizado, sob nenhuma hipótese, como substituto do estudo pessoal da Bíblia, da oração e da direção do Espírito Santo, nem como conselho pastoral, jurídico, médico, psicológico ou normativo oficial de igrejas/denominações.**


## Aviso Legal e Isenção de Responsabilidade (Disclaimer)

1. **Uso de IA e Possibilidade de Erros:** As respostas são processadas através de modelos de Inteligência Artificial Generativa. Modelos de IA estão sujeitos a imprecisões, erros de interpretação ou criar conteúdos divergentes. O usuário deve sempre validar qualquer conteúdo recebido diretamente no texto bíblico e com lideranças pastorais idôneas.
2. **Caráter Auxiliar:** Este software é uma ferramenta assistente. Nenhuma resposta gerada pela API possui caráter doutrinário, normativo ou dogmático.
3. **Isenção de Garantia ("AS IS"):** O software é fornecido "no estado em que se encontra" (*AS IS*), sem garantias de qualquer tipo, expressas ou implícitas. O autor não se responsabiliza por usos indevidos e quaisquer danos, perdas ou decisões tomadas com base nas informações geradas por este sistema.


## Metodologia (SDD)

Este projeto utiliza **Spec-Driven Development (SDD)**.  
Toda alteração estrutural deve primeiro ser refletida nas especificações:  

- **[Especificação de Produto](docs/product.md)**
- **[Especificação de Arquitetura](docs/architecture.md)**
- **[Decisões Arquiteturais (ADRs)](docs/adr/)**


## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- Docker


## Estrutura do Projeto

A aplicação adota estritamente a **Arquitetura Hexagonal** em um modelo de **microserviço único**:

```
farol-da-fe-api/
 ├── docs/                           
 │    ├── adr/                       # Registros de Decisão Arquitetural 
 │    ├── product.md                 # Registros de especificação de Produto/Plataforma
 │    └── architecture.md            # Documento com detalhamento de arquitetura
 ├── src/
 │    ├── main/
 │    │    ├── java/.../farol_da_fe/
 │    │    │    ├── domain/          
 │    │    │    │    ├── model/      # Entidades de domínio puras, Value Objects e exceções
 │    │    │    │    ├── port/       # Contratos de entrada e saída (Inbound/Outbound Ports)
 │    │    │    │    │    ├── in/    # Use Cases 
 │    │    │    │    │    └── out/   # Interfaces de saída 
 │    │    │    │    └── service/    # Implementações puras das regras de negócio
 │    │    │    └── infrastructure/  
 │    │    │         ├── adapter/
 │    │    │         │    ├── in/web/    # Controllers REST, Webhook do Telegram e DTOs (records)
 │    │    │         │    └── out/
 │    │    │         │         ├── genai/  # GeminiAiAdapter implementando GenAiPort
 │    │    │         │         └── audit/  # GoogleSheetsAdapter implementando AuditRepositoryPort
 │    │    │         ├── config/         # Configurações Spring, Virtual Threads, CORS, Beans
 │    │    │         └── guardrail/      # Filtros anti-injection, sanitização e limites
 │    │    └── resources/
 │    │         ├── prompts/         # Prompts de sistema executáveis
 │    │         └── application.properties # Configurações de ambiente do Spring Boot
 │    └── test/                      
 ├── AGENTS.md                       # Diretrizes arquiteturais para Agentes IA
 ├── skills.md                       # Alias de referência rápida para AGENTS.md
 ├── Dockerfile                      # Configurações de provisionamento de container
 └── pom.xml                         # Configurações gerais do projeto
```

---

## Licença

Este projeto está licenciado sob a licença **Creative Commons Atribuição-NãoComercial-CompartilhaIgual 4.0 Internacional (CC BY-NC-SA 4.0)**. 

Você pode compartilhar e adaptar o material para fins não comerciais, desde que atribua o crédito ao projeto original e distribua sob a mesma licença. Veja detalhes em [LICENSE.md](LICENSE.md) e [TERMS.md](TERMS.md).