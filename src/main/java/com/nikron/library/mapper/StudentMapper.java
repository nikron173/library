package com.nikron.library.mapper;

import com.nikron.library.dto.StudentCreateDTO;
import com.nikron.library.dto.StudentDTO;
import com.nikron.library.entity.Book;
import com.nikron.library.entity.Student;
import com.nikron.library.exception.ValidationDateException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Component
public class StudentMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public StudentDTO studentToDTO(Student student){
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(Period.between(student.getBirthDay(), LocalDate.now()).getYears());
        dto.setEmail(student.getEmail());
        dto.setBooks(student.getBooks() != null ?
                student.getBooks().stream().map(Book::getTitle).toList() : null);
        return dto;
    }

    public Student dtoToStudent(StudentCreateDTO dto){
        return new Student(dto.getName(),
                dto.getEmail(),
                convertStringToLocalDate(dto.getBirthDay())
        );
    }

    private LocalDate convertStringToLocalDate(String strDate){
        LocalDate date = LocalDate.parse(strDate, formatter);
        if (LocalDate.now().isAfter(date)){
            return date;
        }
        throw new ValidationDateException("Birth day not valid");
    }
}
