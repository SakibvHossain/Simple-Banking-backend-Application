package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.service.AccountService;
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
public class AccountController {
    private AccountService service;
    public static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("createAccount")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO dto){
        log.info("Account creating!!!");
        return new ResponseEntity<>(service.addAccount(dto), HttpStatus.CREATED);
    }
    @GetMapping("holderName/{name}")
    @Cacheable(value = "account", key = "#name")
    public Account getAccountByName(@PathVariable String name){
        return service.getAccount(name);
    }

    //ResponseEntity along with DTO doesn't work while working with redis as a caching mechanism
    @PutMapping("{id}/deposit")
    @CachePut(cacheNames = "account", key = "#id")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("balance");
        return service.deposit(id, value);
    }
    @PutMapping("{id}/withdraw")
    @CachePut(cacheNames = "accountdto", key = "#id")
    public AccountDTO withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("withdraw_balance");
        return service.withdraw(id,value);
    }
    @GetMapping("allAccounts")
    public ResponseEntity<List<AccountDTO>> getAllAccount(){
        return new ResponseEntity<>(service.getAllAccounts(),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("deleteAccount/{id}")
    @CacheEvict(cacheNames = "account", key = "#id", beforeInvocation = true)
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Account has been deleted!!!", HttpStatus.ACCEPTED);
    }
}
