## 1. Domain Model

- [x] 1.1 Create `BookStatus` enum with values UNREAD, READING, FINISHED
- [x] 1.2 Create `Book` JPA entity with fields: id (UUID), title, author, status, addedAt
- [x] 1.3 Create `BookRequest` DTO with Bean Validation annotations (@NotBlank for title and author)
- [x] 1.4 Create `BookRepository` extending JpaRepository with findAllByOrderByAddedAtDesc method

## 2. Service Layer

- [ ] 2.1 Create `BookService` with createBook method (sets id and addedAt server-side)
- [ ] 2.2 Add getAllBooks method returning books ordered by addedAt descending
- [ ] 2.3 Add getBookById method throwing ResourceNotFoundException when not found
- [ ] 2.4 Add updateBook method throwing ResourceNotFoundException when not found
- [ ] 2.5 Add deleteBook method throwing ResourceNotFoundException when not found
- [ ] 2.6 Create `ResourceNotFoundException` mapped to HTTP 404

## 3. REST API

- [ ] 3.1 Create `BookController` with POST /books returning 201
- [ ] 3.2 Add GET /books endpoint returning 200 with list
- [ ] 3.3 Add GET /books/{id} endpoint returning 200 or 404
- [ ] 3.4 Add PUT /books/{id} endpoint returning 200 or 404
- [ ] 3.5 Add DELETE /books/{id} endpoint returning 204 or 404
- [ ] 3.6 Add global `@ControllerAdvice` to handle validation errors (400) and ResourceNotFoundException (404)

## 4. Thymeleaf UI

- [ ] 4.1 Create `BookViewController` with GET / returning all books as model attribute
- [ ] 4.2 Create `templates/books/list.html` displaying title, author, status, addedAt for each book
- [ ] 4.3 Add delete button per book row that calls DELETE /books/{id} via form POST with method override
