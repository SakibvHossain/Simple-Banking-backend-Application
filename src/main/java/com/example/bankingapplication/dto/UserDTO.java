package com.example.bankingapplication.dto;

import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "User Data Transfer Object Model"
)
public class UserDTO implements Serializable {
    private Long id;
    @Schema(
            description = "Username field property"
    )
    private String username;
    @Schema(
            description = "email field property"
    )
    private String email;
    @Schema(
            description = "password field property"
    )
    private String password;
    @Schema(
            description = "account field property"
    )
    private Account account;
    @Schema(
            description = "address field property"
    )
    private Address address;
}
