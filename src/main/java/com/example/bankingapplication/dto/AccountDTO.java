package com.example.bankingapplication.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
        private Long id;
        @NotEmpty(message = "Holder name should not be empty")
        private String holderName;
        @NotNull(message = "Balance should not be empty")
        private Double balance;
}
