package com.example.bankingapplication.service;

import com.example.bankingapplication.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO addAccount(AccountDTO dto);
    AccountDTO getAccount(String name);
    AccountDTO deposit(Long id, double balance);
    AccountDTO withdraw(Long id, double balance);
    List<AccountDTO> getAllAccounts();
    void delete(Long id);
}
