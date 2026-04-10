package dev.readinglist.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void should_returnEmpty_when_noBooksSaved() {
        List<Book> books = bookRepository.findAllByOrderByAddedAtDesc();
        assertTrue(books.isEmpty());
    }

    @Test
    void should_persistAndRetrieveBook_when_bookIsSaved() {
        Book book = new Book();
        book.setTitle("Domain-Driven Design");
        book.setAuthor("Eric Evans");
        book.setStatus(BookStatus.UNREAD);
        book.setAddedAt(LocalDateTime.now());

        Book saved = bookRepository.save(book);

        assertNotNull(saved.getId());
        Optional<Book> found = bookRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Domain-Driven Design", found.get().getTitle());
        assertEquals("Eric Evans", found.get().getAuthor());
        assertEquals(BookStatus.UNREAD, found.get().getStatus());
    }

    @Test
    void should_returnBooksOrderedByAddedAtDesc_when_multipleBooksExist() {
        LocalDateTime earlier = LocalDateTime.now().minusDays(2);
        LocalDateTime later = LocalDateTime.now();

        Book older = new Book();
        older.setTitle("Old Book");
        older.setAuthor("Author A");
        older.setStatus(BookStatus.FINISHED);
        older.setAddedAt(earlier);

        Book newer = new Book();
        newer.setTitle("New Book");
        newer.setAuthor("Author B");
        newer.setStatus(BookStatus.READING);
        newer.setAddedAt(later);

        bookRepository.save(older);
        bookRepository.save(newer);

        List<Book> books = bookRepository.findAllByOrderByAddedAtDesc();
        assertEquals(2, books.size());
        assertEquals("New Book", books.get(0).getTitle());
        assertEquals("Old Book", books.get(1).getTitle());
    }

    @Test
    void should_deleteBook_when_bookExists() {
        Book book = new Book();
        book.setTitle("To Delete");
        book.setAuthor("Author");
        book.setStatus(BookStatus.UNREAD);
        book.setAddedAt(LocalDateTime.now());

        Book saved = bookRepository.save(book);
        bookRepository.deleteById(saved.getId());

        assertTrue(bookRepository.findById(saved.getId()).isEmpty());
    }
}
