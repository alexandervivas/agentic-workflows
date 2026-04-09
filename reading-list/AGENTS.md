# Reading List — Agent Instructions

## Project
Spring Boot 3.5.13 REST API + Thymeleaf UI. Java 21. H2 in-memory database. Maven build.

## Commands

### Build
```
mvn compile
```

### Test
```
mvn test
```

### Package
```
mvn package -DskipTests
```

### Checkstyle
```
mvn checkstyle:check
```

## Structure
```
src/main/java/dev/readinglist/   — application source
src/test/java/dev/readinglist/   — JUnit 5 tests
src/main/resources/templates/    — Thymeleaf templates
src/main/resources/              — application.properties
openspec/changes/                — OpenSpec change artifacts
.opencode/agents/                — subagent definitions
```

## Conventions
- Package root: `dev.readinglist`
- No comments unless explicitly asked
- JUnit 5 for all tests — no JUnit 4
- Test naming: `should_<expected>_when_<condition>`
- REST responses use standard Spring `ResponseEntity`
- Validation via `spring-boot-starter-validation` annotations
- No Lombok — plain Java

## Subagents
- `@coder` — implements features from OpenSpec tasks.md
- `@tester` — writes and runs JUnit 5 tests
- `@reviewer` — reviews code, emits structured JSON findings
- `@validator` — validates implementation against OpenSpec specs/

## Workflow

When implementing any OpenSpec change (e.g. after `/opsx:apply`), do NOT implement
tasks directly. Delegate each phase to the appropriate subagent using the cycle below.

### Per-task cycle

For each pending task in `tasks.md`, execute in order:

1. **@coder** — provide the task ID, relevant spec file(s), and design.md
   - Wait for structured output: `TASK`, `FILES_CHANGED`, `FINDINGS_ADDRESSED`

2. **@tester** — provide the files reported in `FILES_CHANGED` by @coder
   - Wait for structured output: `RESULT` (PASS/FAIL) and `FAILURES`
   - On FAIL: pass failure details back to @coder and re-run @tester (counts as one retry)

3. **@reviewer** — provide the files reported in `FILES_CHANGED` by @coder
   - Wait for JSON findings: `findings[]` with `id`, `severity`, `status`
   - For each `blocking` finding:
     - Pass finding ID and suggestion back to @coder — max **3 retry attempts**
     - After 3 failed attempts on the same finding: mark as `ESCALATE`, record in
       `deferred.json` with `severity: blocking`, and continue to next task
   - For `warning` findings: track in `deferred.json` but do not block progress
   - On `pass` or `pass_with_warnings` (all blocking resolved): proceed

4. **@validator** — provide the path to `specs/` and the files in `FILES_CHANGED`
   - Wait for JSON output: `overall` (pass / partial / fail) and `requirements[]`
   - On `fail`: pass unmet requirement IDs back to @coder (counts as one retry attempt)
   - On `partial`: record unmet items in `deferred.json` with `risk` field, continue
   - On `pass`: proceed

5. Mark the task `- [x]` in `tasks.md` only when ALL of the following are true:
   - @tester reports `PASS`
   - @reviewer reports `pass` or `pass_with_warnings` (zero open blocking findings)
   - @validator reports `pass` or `partial` (partial items recorded in deferred.json)

### Deferred items format

Maintain `deferred.json` at the project root. Append — never overwrite — each entry:

```json
{
  "task_id": "<task-id>",
  "type": "reviewer-finding | validator-partial | escalation",
  "finding_id": "<Rn or requirement id>",
  "description": "<issue description>",
  "severity": "blocking | warning | low | medium | high",
  "reason": "<why it was deferred>",
  "created_at": "<ISO date>"
}
```

### Session-end gate

Before suggesting `/opsx:archive`, verify:
- Zero open blocking findings across all tasks
- All deferred items are recorded in `deferred.json` with a non-empty `reason`
- All tasks in `tasks.md` are marked `- [x]`
