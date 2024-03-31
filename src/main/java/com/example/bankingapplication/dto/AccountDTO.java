package com.example.bankingapplication.dto;

import com.example.bankingapplication.entity.Address;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements Serializable {
        private Long id;
        @NotEmpty(message = "Holder name should not be empty")
        private String holderName;
        @NotNull(message = "Balance should not be empty")
        private Double balance;
        private Address address;
}
