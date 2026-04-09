# Agentic SDD Workspace

This workspace is a hands-on exercise in combining **Spec-Driven Development (SDD)** with **agentic workflows** using [opencode](https://opencode.ai) and [OpenSpec](https://github.com/Fission-AI/OpenSpec).

## What This Explores

The exercise demonstrates how to structure an AI-assisted development cycle where:

- A **spec** (not a chat prompt) is the source of truth for every decision
- Specialized **subagents** own distinct phases of the cycle — coding, testing, reviewing, and validating
- **Reviewer findings** are structured, trackable, and subject to a circuit breaker to prevent infinite loops
- **Deferred items** are documented explicitly rather than silently skipped
- The orchestrator enforces a **session-end gate** before any change is archived

## Stack

- **AI runtime**: opencode with AWS Bedrock
- **Spec framework**: OpenSpec (spec-driven schema)
- **Application**: Java 21 + Spring Boot 3.5.13 (see `reading-list/`)

## Subagents

| Agent | Model | Role |
|---|---|---|
| `@coder` | Claude Sonnet 4.6 | Implements tasks from `tasks.md` |
| `@tester` | Claude Sonnet 4.6 | Writes and runs JUnit 5 tests |
| `@reviewer` | Claude Opus 4.6 | Emits structured JSON findings |
| `@validator` | Claude Haiku 4.5 | Validates implementation against `specs/` |

## Per-task Cycle

```
@coder implements task
  → @tester writes & runs tests (PASS/FAIL)
  → @reviewer emits findings (blocking/warning, max 3 retries)
  → @validator checks spec conformance (pass/partial/fail)
  → deferred items logged to deferred.json
  → task marked complete
```

## Projects

- [`reading-list/`](./reading-list/) — pet project used to validate the full cycle end-to-end
