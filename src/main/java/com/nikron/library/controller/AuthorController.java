package com.nikron.library.controller;

import com.nikron.library.entity.Author;
import com.nikron.library.exception.ValidationException;
import com.nikron.library.service.AuthorService;
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
@RequestMapping(path = "/author", produces = "application/json")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<?> getAuthors(@RequestParam(required = false) UUID id){
        if (id != null){
            return new ResponseEntity<>(authorService.getAuthor(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(authorService.getAuthor(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author,
                                            BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Author sAuthor = authorService.save(author);
        return new ResponseEntity<>(sAuthor, HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<Author> changeAuthor(@PathVariable UUID id,
                                               @Valid @RequestBody Author author,
                                               BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Author uAuthor = authorService.update(id, author);
        return new ResponseEntity<>(uAuthor, HttpStatus.OK);
    }
}
