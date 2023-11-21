package com.nikron.library.service;

import com.nikron.library.entity.Book;
import com.nikron.library.entity.Student;
import com.nikron.library.exception.ResourceAlreadyExistsException;
import com.nikron.library.exception.ResourceNotFoundException;
import com.nikron.library.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final BookService bookService;

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public Student getStudent(UUID id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student id " + id + " not found"));
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student update(UUID id, Student student) {
        Student uStudent = getStudent(id);
        if (uStudent.getName().equals(student.getName())){
            return uStudent;
        }
        uStudent.setName(student.getName());
        return uStudent;
    }

    @Transactional
    public Student addBook(UUID id, String title) {
        Student student = getStudent(id);
        Book book = bookService.getBook(title);
        if (student.getBooks().contains(book)){
            throw new ResourceAlreadyExistsException("The student already has a "
                    + book.getTitle() + " book");
        }
        if (!Objects.isNull(book.getStudent())){
            throw new ResourceAlreadyExistsException("The book "
                    + book.getTitle() + " is already occupied");
        }
        book.setStudent(student);
        student.getBooks().add(book);
        return student;
    }
}
