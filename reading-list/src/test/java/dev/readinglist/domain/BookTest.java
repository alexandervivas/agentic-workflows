package dev.readinglist.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {

    @Test
    void should_storeAndRetrieveAllFields_when_settersAreCalled() {
        Book book = new Book();
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        book.setId(id);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setStatus(BookStatus.UNREAD);
        book.setAddedAt(now);

        assertEquals(id, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(BookStatus.UNREAD, book.getStatus());
        assertEquals(now, book.getAddedAt());
    }

    @Test
    void should_storeReadingStatus_when_statusIsSetToReading() {
        Book book = new Book();
        book.setStatus(BookStatus.READING);
        assertEquals(BookStatus.READING, book.getStatus());
    }

    @Test
    void should_storeFinishedStatus_when_statusIsSetToFinished() {
        Book book = new Book();
        book.setStatus(BookStatus.FINISHED);
        assertEquals(BookStatus.FINISHED, book.getStatus());
    }
}
