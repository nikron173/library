package com.nikron.library.dto;

import com.nikron.library.entity.Book;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private UUID id;

    @NotBlank(message = "Student name null.")
    @Size(min = 3, max = 30, message = "Student name 3-30 symbols.")
    private String name;

    @Email(message = "Not correctly email.")
    private String email;

    @Positive(message = "Age not negative.")
    private int age;

    private List<String> books;
}
