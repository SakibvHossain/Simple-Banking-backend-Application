package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AddressDTO;
import com.example.bankingapplication.dto.UserDTO;
import com.example.bankingapplication.service.AddressService;
import com.example.bankingapplication.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@AllArgsConstructor
@CacheConfig(cacheNames = "user")
@Tag(
        name = "User Managing REST API",
        description = "User Managing REST API has CRUD operations like Create, Update, Delete, Read"
)
public class UserController {
    private UserService service;
    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("createUser")
    @Operation(
            summary = "Create User REST API",
            description = "The API will create new user"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<UserDTO> createAddress(@RequestBody @Valid UserDTO dto){
        log.info("User creating!!!");
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("getUser/{name}")
    @Operation(
            summary = "Get User REST API",
            description = "The API will get existed user by name"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<UserDTO> userDetailsByName(@PathVariable String name){
        return new ResponseEntity<>(service.getUserDetailsByName(name), HttpStatus.ACCEPTED);
    }
}
