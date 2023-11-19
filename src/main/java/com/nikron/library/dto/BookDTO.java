package com.nikron.library.dto;

import com.nikron.library.entity.Student;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private UUID id;

    @NonNull
    @Size(min = 3, max = 50, message = "Book title 3-50 symbols.")
    @NotBlank(message = "Title is null.")
    private String title;

    @NonNull
    @NotBlank(message = "Author name null.")
    @Size(min = 3, max = 30, message = "Author name 3-30 symbols.")
    private String author;

    @NonNull
    @NotBlank(message = "Genre name null.")
    @Size(min = 3, max = 30, message = "Genre name 3-30 symbols.")
    private String genre;

    @NonNull
    @PositiveOrZero(message = "Book price cannot be below zero.")
    private BigDecimal price;

    private StudentDTO student;
}
