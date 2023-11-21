package com.nikron.library.service;

import com.nikron.library.entity.Genre;
import com.nikron.library.exception.ResourceAlreadyExistsException;
import com.nikron.library.exception.ResourceNotFoundException;
import com.nikron.library.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> getGenre(){
        return genreRepository.findAll();
    }

    public Genre getGenre(UUID id){
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre id " + id + " not found."));
    }

    public Genre getGenre(String name){
        return genreRepository.findGenreByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Genre name " + name + " not found."));
    }

    public Genre save(Genre genre) {
        Optional<Genre> cGenre = genreRepository.findGenreByName(genre.getName());
        if (cGenre.isPresent()){
            throw new ResourceAlreadyExistsException("Genre name " + genre.getName() + " already exists.");
        }
        return genreRepository.save(genre);
    }

    @Transactional
    public Genre update(UUID id, Genre genre) {
        Genre uGenre = getGenre(id);
        if (uGenre.getName().equals(genre.getName())){
            return uGenre;
        }
        uGenre.setName(genre.getName());
        return uGenre;
    }
}
