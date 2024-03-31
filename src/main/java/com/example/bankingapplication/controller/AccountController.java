package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bank")
@AllArgsConstructor
@CacheConfig(cacheNames = "account")
@Tag(
        name = "Account Managing REST API",
        description = "Account Managing REST API has CRUD operations like Create, Update, Delete, Read"
)
public class AccountController {
    private AccountService service;
    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("createAccount")
    @Operation(
            summary = "Create Account REST API",
            description = "The API will create account for the user"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO dto){
        log.info("Account creating!!!");
        return new ResponseEntity<>(service.addAccount(dto), HttpStatus.CREATED);
    }
    @GetMapping("holderName/{name}")
    @Cacheable(value = "account", key = "#name")
    @Operation(
            summary = "Get account REST API",
            description = "The API will get account by name"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public Account getAccountByName(@PathVariable String name){
        return service.getAccount(name);
    }

    //ResponseEntity along with DTO doesn't work while working with redis as a caching mechanism
    @PutMapping("{id}/deposit")
    @CachePut(cacheNames = "account", key = "#id")
    @Operation(
            summary = "Deposit account REST API",
            description = "The API will deposit balance to the account"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("balance");
        return service.deposit(id, value);
    }
    @PutMapping("{id}/withdraw")
    @CachePut(cacheNames = "accountdto", key = "#id")
    @Operation(
            summary = "Withdraw account REST API",
            description = "The API will withdraw account balance"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public AccountDTO withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("withdraw_balance");
        return service.withdraw(id,value);
    }
    @GetMapping("allAccounts")
    @Operation(
            summary = "Get All user REST API",
            description = "The API will get all the user holding the account"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        return new ResponseEntity<>(service.getAllAccounts(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("deleteAccount/{id}")
    @Operation(
            summary = "Delete account REST API",
            description = "The API will delete account"
    )
    @ApiResponse(
            responseCode = "201 Created!",
            description = "HTTP Status 201 Created"
    )
    @CacheEvict(cacheNames = "account", key = "#id", beforeInvocation = true)
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Account has been deleted!!!", HttpStatus.ACCEPTED);
    }
}
