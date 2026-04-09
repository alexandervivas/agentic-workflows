# Reading List

A personal reading list manager built with Spring Boot 3.5.13. Track books you want to read, are currently reading, or have finished.

## Features

- Add, view, update and delete books
- Track reading status: `UNREAD`, `READING`, `FINISHED`
- Server-rendered UI with Thymeleaf
- REST API returning JSON
- H2 in-memory database (no setup required)

## Tech Stack

- Java 21
- Spring Boot 3.5.13
- Spring Data JPA + H2
- Thymeleaf
- Bean Validation
- JUnit 5 + MockMvc

## Running the App

```bash
mvn spring-boot:run
```

App starts at `http://localhost:8080`.
H2 console available at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:readinglistdb`).

## Building

```bash
mvn compile        # compile
mvn test           # run tests
mvn package        # build jar (skips tests: add -DskipTests)
mvn checkstyle:check  # lint
```

## REST API

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/books` | Add a book |
| `GET` | `/books` | List all books |
| `GET` | `/books/{id}` | Get a book by ID |
| `PUT` | `/books/{id}` | Update a book |
| `DELETE` | `/books/{id}` | Delete a book |

### Book fields

```json
{
  "id": "uuid",
  "title": "string",
  "author": "string",
  "status": "UNREAD | READING | FINISHED",
  "addedAt": "ISO datetime"
}
```

## Project Structure

```
src/main/java/dev/readinglist/   — application source
src/test/java/dev/readinglist/   — JUnit 5 tests
src/main/resources/templates/    — Thymeleaf templates
openspec/changes/book-crud/      — OpenSpec spec artifacts for the first feature
.opencode/agents/                — subagent definitions (coder, tester, reviewer, validator)
AGENTS.md                        — build commands and orchestration workflow
```
