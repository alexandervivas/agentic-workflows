package dev.readinglist.domain;

import jakarta.validation.constraints.NotBlank;

public class BookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private BookStatus status = BookStatus.UNREAD;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
