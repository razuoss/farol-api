# AGENTS.md (AI Agent Guidelines)

> This document is written in English to optimize LLM token consumption and to maximize instruction adherence for architectural constraints. Domain-specific theological vocabulary and ubiquitous language terms are preserved in Brazilian Portuguese.

---

## 1. Project Identity
- **Project Name**: Farol da Fé API (`farol-da-fe-api`)
- **Domain**: Guided Bible study and exegetical analysis assistant for Christians powered by Generative AI.
- **Core Mission**: Provide sound exegetical analysis (historical context, audience, original languages, and practical application) while maintaining strict theological guardrails and safety.
- **Ubiquitous Language (Domain Vocabulary in Brazilian Portuguese)**:
  - `exegese`: Exegetical study of a biblical passage or theme (contexto histórico, análise do texto, aplicação prática).
  - `guardrail`: Input/output security and theological filters that reject non-biblical/out-of-scope requests and block prompt injection.
  - `devocional`: Practical devotional application format.
  - `sermao`: Homiletic/sermon outline format.

---

## 2. Tech Stack & Versions
- **Language**: Java 21 (leveraging **Virtual Threads** for blocking I/O)
- **Framework**: Spring Boot 4.x
- **Documentation**: SpringDoc OpenAPI (Swagger UI at `/swagger-ui.html`)
- **GenAI Provider**: GOogle Gemini, via synchronous HTTP POST + JSON Schema Structured Output (ADR-006)
- **Resilience & Security**:
  - **Bucket4j**: In-memory rate limiting (2 requests/minute per user; ADR-007).
  - **Resilience4j**: Circuit breaker and timeout management (> 30s timeout; ADR-011).
- **Audit & Persistence**: Google Sheets Adapter (MVP non-blocking audit logging; ADR-009/010).
- **Quality & Testing**: JaCoCo, SpotBugs, SonarCloud, JUnit 5, Spring Boot Test.

---

## 3. Architecture: Inviolable Rules
The project strictly follows **Hexagonal Architecture (Ports & Adapters)** in a **Single Microservice** layout optimized for serverless deployments (ADR-004).

### 3.1 Layer Responsibilities & Boundaries
```
io.github.razuoss.farol_da_fe
 ├── domain/                     # 🚫 NEVER import Spring Boot or Infra frameworks here
 │    ├── model/                 # Pure domain entities, value objects, and domain exceptions
 │    ├── port/
 │    │    ├── in/               # Inbound use case interfaces (e.g., ExegeseUseCase)
 │    │    └── out/              # Outbound interfaces (e.g., GenAiPort, AuditRepositoryPort)
 │    └── service/               # Pure domain logic implementing inbound ports
 ├── infrastructure/             # Spring Boot, frameworks, external integrations
 │    ├── adapter/
 │    │    ├── in/web/           # REST Controllers, Telegram Webhook handlers, DTOs
 │    │    └── out/
 │    │         ├── genai/       # GenAiAdapter implementing GenAiPort
 │    │         └── audit/       # GoogleSheetsAdapter implementing AuditRepositoryPort
 │    ├── config/                # @Configuration, Virtual Thread setup, Beans, CORS, Security
 │    └── guardrail/             # Prompt injection filters, length clipping, domain validators
```

### 3.2 Inviolable Architectural Constraints
1. **Domain Isolation**: Classes under `domain/` must NEVER import `org.springframework.*`, HTTP libraries, or third-party SDKs. All dependencies must point inward.
2. **Port Abstractions**: All external communications (GenAI, Audit Sheets, Databases) MUST be abstracted behind an interface in `domain/port/out/`.
3. **Virtual Threads for GenAI**: Communication with Gen AI is synchronous (`HTTP POST`). Java 21 Virtual Threads must be enabled so blocking I/O never exhausts OS threads (ADR-001).
4. **Fire-and-Forget Audit**: Audit logging via `AuditRepositoryPort` must execute asynchronously (`@Async`). An audit failure must NEVER break or alter the `HTTP 200 OK` returned to the user.

---

## 4. Code Conventions
- **Immutability (DTOs)**: Always use Java `record` for request/response DTOs, GenAI schema structures, and internal data transfer. Do not use mutable classes for payloads.
- **Naming Conventions**:
  - **Inbound Ports**: Describe the use case (e.g., `ExegeseUseCase`, `TelegramWebhookUseCase`).
  - **Outbound Ports**: Suffix with `Port` (e.g., `GenAiPort`, `AuditRepositoryPort`).
  - **Adapters**: Prefix with technology name (e.g., `GeminiAiAdapter`, `GoogleSheetsAuditAdapter`, `ExegeseController`).
- **JSON Parsing**: Never use `Map<String, Object>` or untyped string parsing for AI responses. Always define a typed `record` matching Gemini's JSON Schema Structured Output.
- **Error Handling & HTTP Status Codes**:
  - `HTTP 400 Bad Request`: Input validation failure, prompt injection detected, or request out of theological scope.
  - `HTTP 429 Too Many Requests`: Rate limit exceeded (Bucket4j).
  - `HTTP 503 Service Unavailable`: Gemini API non-200 response or temporary network failure.
  - `HTTP 504 Gateway Timeout`: GenAI call exceeded 30 seconds (Resilience4j).

---

## 5. Strict Prohibitions (What AI NEVER Does)
- **NEVER** place business logic, prompt formatting, or theological validation inside a Spring Controller.
- **NEVER** import `@Component`, `@Service`, `@Autowired`, or `@Transactional` inside `io.github.razuoss.farol_da_fe.domain.*`.
- **NEVER** parse JSON using generic maps (`Map<String, Object>`) or raw string manipulation.
- **NEVER** allow synchronous/blocking audit logging in the main request-response thread.
- **NEVER** send raw user input to Gemini without prior length truncation (max 1000 characters) and guardrail validation.
- **NEVER** expose internal stack traces or API keys in error responses.

---

## 6. Key ADR References
When modifying or reviewing code, adhere to the Architecture Decision Records in `docs/adr/`:

| ADR | Decision | Focus Area |
| :--- | :--- | :--- |
| **ADR-001** | Java 21 & Spring Boot 3 | Virtual Threads & core framework |
| **ADR-004** | Single Microservice & Serverless | Cloud Run / Free tier optimization |
| **ADR-006** | Google Gemini Generative AI | System instructions & JSON Schema output |
| **ADR-007** | Load Estimates & Bucket4j | Rate limiting (`2 req/min`) & limits |
| **ADR-008** | Theological Endpoints Roadmap | `POST /v1/exegese`, `/devocional`, `/sermao` |
| **ADR-009** | Audit Persistence Migration | Google Sheets MVP -> MongoDB/PostgreSQL |
| **ADR-011** | Resilience4j Circuit Breaker | 30s timeout & failure fallback |
