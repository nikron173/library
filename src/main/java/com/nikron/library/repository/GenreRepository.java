package com.nikron.library.repository;

import com.nikron.library.entity.Author;
import com.nikron.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, UUID> {

    @Query("FROM Genre WHERE name = :name")
    Optional<Genre> findGenreByName(String name);
}
