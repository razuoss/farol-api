# Farol da Fé
## API para estudo bíblico exegético e devocional com IA

O **Farol da Fé** é um assistente que visa apoiar cristãos com conteúdo informativo de qualidade para entendimento e estudos dos textos bíblicos, auxiliando no cruzamento de dados históricos, culturais e textuais.

A motivação deste projeto nasceu da convergência entre duas jornadas do autor: sua especialização em engenharia de software moderna e sua jornada como estudante de teologia. Por isso, mesmo como um projeto experimental e autoral, a **Farol da Fé API** adota padrões de mercado de alta maturidade.
  
---

## Propósito  

O **Farol da Fé** é um assistente pensado para auxiliar cristãos no estudo aprofundado das Escrituras e aplicação devocional, na organização de ideias, correlações teológicas e contexto histórico, buscando estar mais próximo possível da fidelidade textual e melhorar interpretações superficiais ou distorcidas. Para isso, o sistema utiliza Inteligência Artificial Generativa com instruções criteriosas para uma maior assertividade teológica.

O conteúdo gerado por este software destina-se **exclusivamente** ao apoio em estudos e devocionais com viés protestante histórico. **Não deve ser utilizado, sob nenhuma hipótese, como substituto do estudo pessoal da Bíblia, da oração e da direção do Espírito Santo, nem como conselho pastoral, jurídico, médico, psicológico ou normativo oficial de igrejas/denominações.**

---

## Como executar o projeto

Você pode executar o repositório localmente da seguinte maneira:

### 1. Obter o Código

Se você quiser explorar o projeto:

```bash
git clone https://github.com/razuoss/farol-api.git
cd farol-api
```

### 2. Rodar a Aplicação

Para rodar o projeto localmente com as ferramentas padrão:

1. Instale as dependências com `mvn clean install`.
2. Rode o servidor com `mvn spring-boot:run`.

Para rodar com Podman ou Docker:

```bash
podman-compose up --build -d
# ou
docker-compose up --build -d
```

> **Nota sobre Contribuições:** Atualmente, o projeto está em fase inicial de desenvolvimento (MVP). Portanto, **não estamos aceitando contribuições externas de código**. Para mais detalhes, veja o [Guia de Contribuições](CONTRIBUTING.md).

---

## Aviso Legal e Isenção de Responsabilidade

Este software é fornecido no estado em que se encontra ("AS IS"), sendo uma ferramenta puramente auxiliar baseada em IA. Para detalhes completos sobre garantias, limitações de uso e isenção de responsabilidades, consulte nossos [Termos de Uso oficiais](TERMS.md).

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Maven
- Docker

---

## 📂 Arquitetura e Comunidade

Para entender as decisões de design, a estrutura de portas e adaptadores, e demais documentações técnicas, consulte:

- 🏛️ **[Arquitetura do Projeto](docs/architecture.md)**
- 📖 **[Regras de Produto e Negócio](docs/product.md)**
- 🤖 **[Diretrizes para IA (AGENTS.md)](AGENTS.md)**

Para interagir com o projeto e a comunidade, leia:

- 🤝 **[Guia de Contribuições](CONTRIBUTING.md)**
- ⚖️ **[Código de Conduta](CODE_OF_CONDUCT.md)**

---

## 📜 Licença

Este projeto está licenciado sob a licença **Creative Commons Atribuição-NãoComercial-CompartilhaIgual 4.0 Internacional (CC BY-NC-SA 4.0)**.  

Você pode compartilhar e adaptar o material para fins não comerciais, desde que atribua o crédito ao projeto original e distribua sob a mesma licença. Veja detalhes em [LICENSE.md](LICENSE.md) e [TERMS.md](TERMS.md).