## ADDED Requirements

### Requirement: Add a book
The system SHALL allow a user to add a book by providing a title, author, and optional status (defaults to UNREAD).

#### Scenario: Successful book creation
- **WHEN** a POST request is made to `/books` with valid title and author
- **THEN** the system returns HTTP 201 with the created book including a generated UUID and addedAt timestamp

#### Scenario: Missing required fields
- **WHEN** a POST request is made to `/books` with a blank title or blank author
- **THEN** the system returns HTTP 400 with a validation error message

### Requirement: List all books
The system SHALL return all books in the reading list ordered by addedAt descending.

#### Scenario: Books exist
- **WHEN** a GET request is made to `/books`
- **THEN** the system returns HTTP 200 with a JSON array of all books ordered by addedAt descending

#### Scenario: No books exist
- **WHEN** a GET request is made to `/books` and the list is empty
- **THEN** the system returns HTTP 200 with an empty JSON array

### Requirement: Get a book by ID
The system SHALL return a single book by its UUID.

#### Scenario: Book found
- **WHEN** a GET request is made to `/books/{id}` with a valid existing UUID
- **THEN** the system returns HTTP 200 with the book

#### Scenario: Book not found
- **WHEN** a GET request is made to `/books/{id}` with a UUID that does not exist
- **THEN** the system returns HTTP 404

### Requirement: Update a book
The system SHALL allow updating a book's title, author, or status by its UUID.

#### Scenario: Successful update
- **WHEN** a PUT request is made to `/books/{id}` with valid fields
- **THEN** the system returns HTTP 200 with the updated book

#### Scenario: Update non-existent book
- **WHEN** a PUT request is made to `/books/{id}` with a UUID that does not exist
- **THEN** the system returns HTTP 404

#### Scenario: Invalid status value
- **WHEN** a PUT request is made to `/books/{id}` with a status not in UNREAD/READING/FINISHED
- **THEN** the system returns HTTP 400

### Requirement: Delete a book
The system SHALL allow removing a book from the list by its UUID.

#### Scenario: Successful deletion
- **WHEN** a DELETE request is made to `/books/{id}` with a valid existing UUID
- **THEN** the system returns HTTP 204 with no body

#### Scenario: Delete non-existent book
- **WHEN** a DELETE request is made to `/books/{id}` with a UUID that does not exist
- **THEN** the system returns HTTP 404

### Requirement: Book list UI
The system SHALL provide a Thymeleaf page at `/` that lists all books and allows navigation to add or delete a book.

#### Scenario: View book list
- **WHEN** a GET request is made to `/`
- **THEN** the system returns HTTP 200 with an HTML page listing all books with their title, author, status, and addedAt
