package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.dto.AddressDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@AllArgsConstructor
@CacheConfig(cacheNames = "address")
@Tag(
        name = "Address Managing REST API",
        description = "Address Managing REST API has CRUD operations like Create, Update, Delete, Read"
)
public class AddressController {
    private AddressService service;
    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("createAddress/{id}")
    @Operation(
            summary = "Create Address REST API",
            description = "The API will create user address"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressDTO dto, @PathVariable Long id){
        log.info("Address creating!!!");
        return new ResponseEntity<>(service.createAddress(dto, id), HttpStatus.CREATED);
    }

    @GetMapping("holderName/{name}")
    @Operation(
            summary = "Get user REST API",
            description = "The API will get user address by name"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    @Cacheable(value = "addressdto", key = "#name")
    public AddressDTO getAccountByName(@PathVariable String name){
        log.info("Getting the account holder address!!");
        return service.getAddressByID(name);
    }

    @PutMapping("update/{id}")
    @Operation(
            summary = "Update REST API",
            description = "The API will update user based on id"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody @Valid AddressDTO dto, @PathVariable Long id){
        return new ResponseEntity<>(service.updateAddress(dto, id), HttpStatus.CREATED);
    }

}
