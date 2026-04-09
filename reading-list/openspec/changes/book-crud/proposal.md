## Why

A personal reading list needs a way to track books — what to read, what's in progress, and what's finished. This is the foundational CRUD capability that all other features will build on.

## What Changes

- Introduce a `Book` entity with fields: `id` (UUID), `title`, `author`, `status` (UNREAD/READING/FINISHED), `addedAt` (LocalDateTime)
- Expose a REST API with five endpoints: create, list, get by ID, update, delete
- Persist books in an H2 in-memory database via Spring Data JPA
- Provide a Thymeleaf UI to list and manage books

## Capabilities

### New Capabilities
- `book-management`: Full CRUD operations for books via REST API and Thymeleaf UI

### Modified Capabilities

## Impact

- New: `Book` entity, `BookRepository`, `BookService`, `BookController`, `BookRequest` DTO
- New: Thymeleaf templates for book list view
- New: H2 schema auto-created by JPA on startup
- No breaking changes — greenfield feature
