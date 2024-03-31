package com.example.bankingapplication.dto;

import com.example.bankingapplication.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
        description = "Address Data Transfer Object Model"
)
public class AddressDTO implements Serializable {
    private Long id;
    @NotEmpty(message = "Street name should not be empty")
    @Schema(
            description = "Street field property"
    )
    private String street;
    @NotEmpty(message = "House name should not be empty")
    @Schema(
            description = "House field property"
    )
    private String house;
    @NotNull(message = "Zipcode should not be empty")
    @Schema(
            description = "Zipcode field property"
    )
    private Integer zipcode;
}
