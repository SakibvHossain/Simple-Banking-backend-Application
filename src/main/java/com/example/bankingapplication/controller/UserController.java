package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AddressDTO;
import com.example.bankingapplication.dto.UserDTO;
import com.example.bankingapplication.service.AddressService;
import com.example.bankingapplication.service.UserService;
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
public class UserController {
    private UserService service;
    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("createUser")
    public ResponseEntity<UserDTO> createAddress(@RequestBody @Valid UserDTO dto){
        log.info("User creating!!!");
        return new ResponseEntity<>(service.createUser(dto), HttpStatus.CREATED);
    }

    @GetMapping("getUser/{name}")
    public ResponseEntity<UserDTO> userDetailsByName(@PathVariable String name){
        return new ResponseEntity<>(service.getUserDetailsByName(name), HttpStatus.ACCEPTED);
    }
}
