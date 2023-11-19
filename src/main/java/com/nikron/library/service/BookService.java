package com.nikron.library.service;

import com.nikron.library.dto.BookDTO;
import com.nikron.library.entity.Author;
import com.nikron.library.entity.Book;
import com.nikron.library.entity.Genre;
import com.nikron.library.exception.ResourceNotFoundException;
import com.nikron.library.repository.AuthorRepository;
import com.nikron.library.repository.BookRepository;
import com.nikron.library.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    public Book getBook(UUID id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book id " + id + " not found"));
    }

    public Book getBook(String title){
        return bookRepository.findBookByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Book title " + title + " not found"));
    }

    @Transactional
    public Book save(BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getPrice());
        Optional<Author> author = authorRepository.findAuthorByName(bookDTO.getAuthor());
        if (author.isEmpty()){
            book.setAuthor(authorRepository.save(new Author(bookDTO.getAuthor())));
        } else {
            book.setAuthor(author.get());
        }
        Optional<Genre> genre = genreRepository.findGenreByName(bookDTO.getGenre());
        if (genre.isEmpty()){
            book.setGenre(genreRepository.save(new Genre(bookDTO.getGenre())));
        } else {
            book.setGenre(genre.get());
        }
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(UUID id, BookDTO bookDTO) {
        Book uBook = getBook(id);
        if (!uBook.getTitle().equals(bookDTO.getTitle())){
            uBook.setTitle(bookDTO.getTitle());
        }
        if (!uBook.getAuthor().getName().equals(bookDTO.getAuthor())){
            Author author = authorRepository.findAuthorByName(bookDTO.getAuthor())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Author name " + bookDTO.getAuthor() + " not found"));
            uBook.setAuthor(author);
        }
        if (!uBook.getGenre().getName().equals(bookDTO.getGenre())){
            Genre genre = genreRepository.findGenreByName(bookDTO.getGenre())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Genre name " + bookDTO.getGenre() + " not found"));
            uBook.setGenre(genre);
        }
        if (!uBook.getPrice().equals(bookDTO.getPrice())){
            uBook.setPrice(bookDTO.getPrice());
        }
        return uBook;
    }
}
