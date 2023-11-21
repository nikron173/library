package com.nikron.library.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private int code;
    private LocalDateTime date;
    private String message;
}
