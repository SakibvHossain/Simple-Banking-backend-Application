package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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

    @PostMapping("createAccount")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO dto){
        return new ResponseEntity<>(service.addAccount(dto), HttpStatus.CREATED);
    }
    @GetMapping("holderName/{name}")
    @Cacheable(value = "account", key = "#name")
    public ResponseEntity<AccountDTO> getAccountByName(@PathVariable String name){
        return new ResponseEntity<>(service.getAccount(name),HttpStatus.ACCEPTED);
    }
    @PutMapping("{id}/deposit")
    @CachePut(cacheNames = "account", key = "#id")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("balance");
        return new ResponseEntity<>(service.deposit(id,value), HttpStatus.ACCEPTED);
    }
    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double value = request.get("withdraw_balance");
        return new ResponseEntity<>(service.withdraw(id,value), HttpStatus.ACCEPTED);
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
