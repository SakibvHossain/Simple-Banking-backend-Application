package com.example.bankingapplication.controller;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bank")
@AllArgsConstructor
public class AccountController {
    private AccountService service;

    @PostMapping("createAccount")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO dto){
        return new ResponseEntity<>(service.addAccount(dto), HttpStatus.CREATED);
    }
    @GetMapping("holderName/{name}")
    public ResponseEntity<AccountDTO> getAccountByName(@PathVariable String name){
        return new ResponseEntity<>(service.getAccount(name),HttpStatus.ACCEPTED);
    }
    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable Long id,@Valid @RequestBody Map<String, Double> request){
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
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Account has been deleted!!!", HttpStatus.ACCEPTED);
    }
}
