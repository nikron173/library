package com.nikron.library.controller;

import com.nikron.library.dto.StudentCreateDTO;
import com.nikron.library.dto.StudentDTO;
import com.nikron.library.entity.Student;
import com.nikron.library.exception.ValidationException;
import com.nikron.library.mapper.StudentMapper;
import com.nikron.library.service.StudentService;
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
@RequestMapping(path = "/student", produces = "application/json")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper mapper;

    @GetMapping
    public ResponseEntity<?> getStudent(@RequestParam(required = false) UUID id){
        if (id != null){
            return new ResponseEntity<>(mapper.studentToDTO(studentService.getStudent(id)),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(studentService.getStudent()
                .stream().map(mapper::studentToDTO).toList(),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentCreateDTO student,
                                                 BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Student sStudent = studentService.save(mapper.dtoToStudent(student));
        return new ResponseEntity<>(mapper.studentToDTO(sStudent), HttpStatus.CREATED);
    }

    @PatchMapping(path = "{id}")
    public ResponseEntity<StudentDTO> changeStudent(@PathVariable UUID id,
                                                    @Valid @RequestBody StudentCreateDTO student,
                                                    BindingResult errors){
        if (errors.hasErrors()){
            throw new ValidationException(errors);
        }
        Student uStudent = studentService.update(id, mapper.dtoToStudent(student));
        return new ResponseEntity<>(mapper.studentToDTO(uStudent), HttpStatus.OK);
    }

    @PatchMapping(path = "{id}/add")
    public ResponseEntity<StudentDTO> addBookStudent(@PathVariable UUID id,
                                                    @RequestParam String title) {

        Student uStudent = studentService.addBook(id, title);
        return new ResponseEntity<>(mapper.studentToDTO(uStudent), HttpStatus.OK);
    }
}


