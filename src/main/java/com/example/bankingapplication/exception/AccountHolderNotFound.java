package com.example.bankingapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountHolderNotFound extends RuntimeException{
    private String holderName;
    public AccountHolderNotFound(String holderName){
        super(String.format("Account Holder Not Found with Name %s", holderName));
        this.holderName = holderName;
    }
}
