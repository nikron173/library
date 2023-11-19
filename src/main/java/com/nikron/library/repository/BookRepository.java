package com.nikron.library.repository;

import com.nikron.library.entity.Book;
import com.nikron.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("FROM Book WHERE title = :title")
    Optional<Book> findBookByTitle(String title);
}
