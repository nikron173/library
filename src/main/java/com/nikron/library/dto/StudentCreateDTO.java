package com.nikron.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentCreateDTO {
    @NonNull
    @NotBlank(message = "Student name null.")
    @Size(min = 3, max = 30, message = "Student name 3-30 symbols.")
    private String name;

    @Email(message = "Not correctly email.")
    @NotBlank
    @NonNull
    private String email;

    @NonNull
    @Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "Birth day mask dd-MM-yyyy")
    private String birthDay;
}
