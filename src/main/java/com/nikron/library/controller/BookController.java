package com.nikron.library.controller;

import com.nikron.library.dto.BookDTO;
import com.nikron.library.dto.StudentDTO;
import com.nikron.library.entity.Book;
import com.nikron.library.entity.Student;
import com.nikron.library.exception.ValidationException;
import com.nikron.library.mapper.BookMapper;
import com.nikron.library.service.BookService;
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
@RequiredArgsConstructor
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper mapper;

    @GetMapping
    public ResponseEntity<?> getBook(@RequestParam(required = false) UUID id){
        if (id != null){
            return new ResponseEntity<>(mapper.bookToDTO(bookService.getBook(id)),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(bookService.getBook().stream().map(mapper::bookToDTO).toList(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO book,
                                           BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Book sBook = bookService.save(book);
        return new ResponseEntity<>(mapper.bookToDTO(sBook), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<BookDTO> changeBook(@PathVariable UUID id,
                                              @Valid @RequestBody BookDTO book,
                                              BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Book uBook = bookService.update(id, book);
        return new ResponseEntity<>(mapper.bookToDTO(uBook), HttpStatus.OK);
    }
}
