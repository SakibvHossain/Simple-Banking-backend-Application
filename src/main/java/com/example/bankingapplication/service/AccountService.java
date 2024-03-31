package com.example.bankingapplication.service;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDTO addAccount(AccountDTO dto);
    Account getAccount(String name);
    Account deposit(Long id, double balance);
    AccountDTO withdraw(Long id, double balance);
    List<AccountDTO> getAllAccounts();
    void delete(Long id);
}
