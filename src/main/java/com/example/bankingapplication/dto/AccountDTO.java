package com.example.bankingapplication.dto;

import com.example.bankingapplication.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(
        description = "Account Data Transfer Object Model"
)
public class AccountDTO implements Serializable {
        private Long id;
        @NotEmpty(message = "Holder name should not be empty")
        @Schema(
                description = "Account Holder name field property"
        )
        private String holderName;
        @NotNull(message = "Balance should not be empty")
        @Schema(
                description = "Balance field property"
        )
        private Double balance;
        @Schema(
                description = "Address field property"
        )
        private Address address;
}
