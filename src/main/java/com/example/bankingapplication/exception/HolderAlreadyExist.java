package com.example.bankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HolderAlreadyExist extends RuntimeException{
    private String message;
    public HolderAlreadyExist(String message){
        super(String.format(message));
        this.message = message;
    }
}
