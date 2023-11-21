package com.nikron.library.controller;

import com.nikron.library.entity.Genre;
import com.nikron.library.exception.ValidationException;
import com.nikron.library.service.GenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping(path = "/genre", produces = "application/json")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<?> getGenre(@RequestParam(required = false) UUID id){
        if (id != null){
            return new ResponseEntity<>(genreService.getGenre(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(genreService.getGenre(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Genre> addAuthor(@Valid @RequestBody Genre genre,
                                           BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Genre sGenre = genreService.save(genre);
        return new ResponseEntity<>(sGenre, HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<Genre> changeGenre(@PathVariable UUID id,
                                             @Valid @RequestBody Genre genre,
                                             BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Genre uGenre = genreService.update(id, genre);
        return new ResponseEntity<>(uGenre, HttpStatus.OK);
    }
}
