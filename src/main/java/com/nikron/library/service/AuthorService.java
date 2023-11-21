package com.nikron.library.service;

import com.nikron.library.entity.Author;
import com.nikron.library.exception.ResourceAlreadyExistsException;
import com.nikron.library.exception.ResourceNotFoundException;
import com.nikron.library.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> getAuthor(){
        return authorRepository.findAll();
    }

    public Author getAuthor(UUID id){
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author id " + id + " not found."));
    }

    public Author getAuthor(String name){
        return authorRepository.findAuthorByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Author name " + name + " not found."));
    }
    public Author save(Author author) {
        Optional<Author> cAuthor = authorRepository.findAuthorByName(author.getName());
        if (cAuthor.isPresent()){
            throw new ResourceAlreadyExistsException("Author name " + author.getName() + " already exists.");
        }
        return authorRepository.save(author);
    }

    @Transactional
    public Author update(UUID id, Author author) {
        Author uAuthor = getAuthor(id);
        if (uAuthor.getName().equals(author.getName())){
            return uAuthor;
        }
        uAuthor.setName(author.getName());
        return uAuthor;
    }
}
