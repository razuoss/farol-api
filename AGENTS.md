# AGENTS.md (AI Agent Guidelines)

> This document is written in English to optimize LLM token consumption. 
> We follow **Spec-Driven Development (SDD)**. This document contains only code-level LLM directives. For domain, product, and architecture rules, you MUST read the specification documents.

## 1. Single Source of Truth (Spec-Driven Development)
Before writing or modifying any code, you MUST consult the following specifications:
- **Product & Domain**: `docs/product.md` (Personas, journeys, domain rules, ubiquitous language)
- **Architecture**: `docs/architecture.md` (Package structure, hexagonal boundaries, technical flows)
- **API Contracts**: `docs/api/openapi.yaml` (REST endpoints, schemas)
- **Decisions**: `docs/adr/` (Context and reasoning)

## 2. Tech Stack & Versions
- **Language**: Java 21 (Virtual Threads enabled)
- **Framework**: Spring Boot 4.x
- **Build**: Maven

## 3. Code Conventions
- **Hexagonal Architecture**: Strictly follow the layer responsibilities and boundaries defined in `docs/architecture.md`.
- **Immutability (DTOs)**: Always use Java `record` for request/response DTOs, GenAI schema structures, and internal data transfer. Do not use mutable classes for payloads.
- **Naming Conventions**:
  - **Inbound Ports**: Describe the use case (e.g., `DevocionalUseCase`, `TelegramWebhookUseCase`).
  - **Outbound Ports**: Suffix with `Port` (e.g., `GenAiPort`, `AuditRepositoryPort`).
  - **Adapters**: Prefix with technology name (e.g., `GeminiAiAdapter`, `GoogleSheetsAuditAdapter`, `DevocionalController`).
- **JSON Parsing**: Never use `Map<String, Object>` or untyped string parsing for AI responses. Always define a typed `record` matching Gemini's JSON Schema Structured Output.

## 4. Strict Prohibitions (What AI NEVER Does)
- **NEVER** ignore the rules defined in `docs/architecture.md` and `docs/product.md`.
- **NEVER** place business logic, prompt formatting, or theological validation inside a Spring Controller.
- **NEVER** import `@Component`, `@Service`, `@Autowired`, ou `@Transactional` inside `io.github.razuoss.farol_da_fe.domain.*`. All domain dependencies must point inward.
- **NEVER** parse JSON using generic maps (`Map<String, Object>`) or raw string manipulation.
- **NEVER** allow synchronous/blocking audit logging in the main request-response thread (use `@Async`).
- **NEVER** send raw user input to GenAI without prior length truncation and guardrail validation.
- **NEVER** expose internal stack traces or API keys in error responses.


