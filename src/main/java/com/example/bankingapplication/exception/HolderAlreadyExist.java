package com.example.bankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class HolderAlreadyExist extends RuntimeException{
    public HolderAlreadyExist(String message){
        super(message);
    }
}
