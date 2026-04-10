package dev.readinglist.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookStatusTest {

    @Test
    void should_haveExactlyThreeValues_when_enumIsDefined() {
        assertEquals(3, BookStatus.values().length);
    }

    @Test
    void should_containUnread_when_enumIsDefined() {
        assertEquals(BookStatus.UNREAD, BookStatus.valueOf("UNREAD"));
    }

    @Test
    void should_containReading_when_enumIsDefined() {
        assertEquals(BookStatus.READING, BookStatus.valueOf("READING"));
    }

    @Test
    void should_containFinished_when_enumIsDefined() {
        assertEquals(BookStatus.FINISHED, BookStatus.valueOf("FINISHED"));
    }
}
