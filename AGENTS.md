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

## 5. Senior Engineering Mindset & Anti-Patterns
Before implementing any solution, evaluate:
1. Is this the most idiomatic approach for Java 21 / Spring Boot 4.x?
2. Does this follow RESTful conventions (proper HTTP verbs, status codes, resource naming)?
3. Would a senior engineer approve this in code review?
4. Is there a simpler, more maintainable alternative?
When in doubt, prefer the approach that is most testable, most readable, and least surprising.

### 5.1 Anti-Patterns (What NOT to do)
| ❌ Anti-Pattern | ✅ Correct Approach | Justification |
|:---|:---|:---|
| `POST /v1/cancelar-item` | `DELETE /v1/itens/{id}` | Use proper RESTful HTTP verbs. |
| `Map<String, Object>` for parsing | `record DevocionalResponse(...)` | Strong typing, compile-time safety. |
| `@Autowired` on private field | Constructor injection | Immutability, testability. |
| Generic `try/catch(Exception e)` | Catch specific domain exceptions | Granular error handling. |
| Nested `if (obj != null)` | `Optional` or early return/fail-fast | Clean code, readability. |
| Business logic in Controller | Delegate to Service via Port | Hexagonal: Controller is a thin adapter. |
| `System.out.println` | SLF4J Logger | Professional observability. |
| String concatenation for URLs | `UriComponentsBuilder` | Security, proper encoding. |
| Hardcoded timeout `30000` | `@Value("${gemini.timeout-seconds}")` | Externalized configuration. |

## 6. Commit, PR & Contribution Guidelines
- For commit message formats, pull request standards, and co-authorship guidelines, **MUST READ**: `CONTRIBUTING.md` and `.github/PULL_REQUEST_TEMPLATE.md`.
