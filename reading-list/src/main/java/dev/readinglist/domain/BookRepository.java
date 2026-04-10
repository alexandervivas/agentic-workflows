package dev.readinglist.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findAllByOrderByAddedAtDesc();
}
