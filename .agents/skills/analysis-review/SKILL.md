---
name: analysis-review
description: Analisa a estrutura completa do sistema e gera documentação técnica padronizada para futura revisão de arquitetura.
---

# Diretrizes da Skill `analysis-review`

Quando o usuário invocar esta skill, siga este fluxo para gerar a documentação técnica a partir do código e da estrutura do projeto:

1. **Análise da Estrutura do Sistema**
   - Inspecione a árvore de diretórios e identifique módulos, pacotes, arquivos de configuração, dependências e padrões de implementação.
   - Priorize informações derivadas de código real, imports, chamadas e configurações.
   - Não invente comportamentos não presentes no código; marque inferências como "hipótese".

2. **Geração de Documento Global "Arquitetura do Sistema"**
   - Descreva a visão arquitetural do projeto.
   - Liste os padrões utilizados e as regras arquiteturais observadas.
   - Explique a separação de responsabilidades e o fluxo de comunicação entre módulos.
   - Identifique dependências críticas, riscos técnicos e acoplamentos importantes.
   - Inclua diretrizes para futuras implementações.

3. **Geração de Documento Global "Objetivo do Sistema"**
   - Defina o propósito principal do sistema.
   - Liste os problemas que ele resolve, os principais fluxos de negócio e os atores envolvidos.
   - Resuma as funcionalidades centrais, a visão de produto e o contexto operacional.

4. **Documentação de Módulos Locais**
   - Para cada módulo/pasta relevante, crie um README local com:
     * objetivo do módulo;
     * responsabilidade principal;
     * funcionalidades existentes;
     * dependências internas e externas;
     * módulos relacionados;
     * pontos de entrada;
     * fluxos importantes;
     * arquivos críticos;
     * observações técnicas e débitos identificados.
   - Garanta consistência entre os documentos globais e os documentos locais.

5. **Detecção de Problemas Arquiteturais**
   - Identifique módulos órfãos, acoplamentos excessivos e violações de arquitetura.
   - Explique dependências entre módulos e como elas afetam o sistema.
   - Destaque riscos de manutenção e áreas que exigem revisão.

6. **Formato e Qualidade**
   - Use linguagem clara, objetiva e orientada à manutenção futura.
   - Produza documentação adequada para uso em Spec Driven Development.
   - Mantenha os artefatos consistentes e alinhados com a estrutura do projeto.
