package com.example.bankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountHolderNotFound extends RuntimeException{
    public AccountHolderNotFound(String holderName){
        super(holderName);
    }
}
