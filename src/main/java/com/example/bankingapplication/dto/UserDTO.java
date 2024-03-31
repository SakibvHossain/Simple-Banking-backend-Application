package com.example.bankingapplication.dto;

import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Account account;
    private Address address;
}
