package com.example.bankingapplication.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {
    private LocalDateTime dateTime;
    private String message; //status
    private String error;
    private String path;
}
