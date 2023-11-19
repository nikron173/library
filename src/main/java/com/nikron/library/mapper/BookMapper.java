package com.nikron.library.mapper;

import com.nikron.library.dto.BookDTO;
import com.nikron.library.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {
    private final StudentMapper mapper;
    public BookDTO bookToDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor().getName());
        dto.setGenre(book.getGenre().getName());
        dto.setPrice(book.getPrice());
        if (book.getStudent() == null){
            return dto;
        }
        dto.setStudent(mapper.studentToDTO(book.getStudent()));
        return dto;
    }
}
