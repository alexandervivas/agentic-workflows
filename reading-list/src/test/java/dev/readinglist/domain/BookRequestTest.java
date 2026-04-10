package dev.readinglist.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void should_passValidation_when_titleAndAuthorAreProvided() {
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("Robert Martin");

        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void should_failValidation_when_titleIsBlank() {
        BookRequest request = new BookRequest();
        request.setTitle("   ");
        request.setAuthor("Robert Martin");

        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void should_failValidation_when_titleIsNull() {
        BookRequest request = new BookRequest();
        request.setTitle(null);
        request.setAuthor("Robert Martin");

        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void should_failValidation_when_authorIsBlank() {
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("   ");

        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("author")));
    }

    @Test
    void should_failValidation_when_authorIsNull() {
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor(null);

        Set<ConstraintViolation<BookRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("author")));
    }

    @Test
    void should_storeStatus_when_statusIsSet() {
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("Robert Martin");
        request.setStatus(BookStatus.READING);

        assertEquals(BookStatus.READING, request.getStatus());
    }

}
