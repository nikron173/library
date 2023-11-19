package com.nikron.library.repository;

import com.nikron.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {
    @Query(value = "FROM Author WHERE name = :name")
    Optional<Author> findAuthorByName(String name);
}
