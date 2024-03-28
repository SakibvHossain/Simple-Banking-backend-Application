package com.example.bankingapplication.service.implementation;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.exception.AccountHolderNotFound;
import com.example.bankingapplication.exception.HolderAlreadyExist;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountRepository repository;
    private ModelMapper mapper;
    @Override
    public AccountDTO addAccount(AccountDTO dto) {
        Account getInfo = repository.findAccountByHolderName(dto.getHolderName());
        if(getInfo != null){
            throw new HolderAlreadyExist("Account holder already exist with name"+dto.getHolderName());
        }

        Account storeDtoToAccount = mapper.map(dto, Account.class);
        Account saveData = repository.save(storeDtoToAccount);
//        AccountDTO storeAccountToDTO = mapper.map(saveData, AccountDTO.class);
        return mapper.map(saveData, AccountDTO.class);
    }

    @Override
    public AccountDTO getAccount(String name) {
        Account account = repository.findAccountByHolderName(name);
        if(account == null || account.getHolderName() == null){
            throw new AccountHolderNotFound(name);
        }
        Account getTheAccount = repository.findAccountByHolderName(name);
        return mapper.map(getTheAccount, AccountDTO.class);
    }

    @Override
    public AccountDTO deposit(Long id, double balance) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountHolderNotFound("Account holder not found"));
        double value = account.getBalance() + balance;
        account.setBalance(value);
        Account save = repository.save(account);
        return mapper.map(save, AccountDTO.class);
    }

    @Override
    public AccountDTO withdraw(Long id, double balance) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountHolderNotFound("Account holder not found"));
        double value = account.getBalance() - balance;
        if(value < 0){
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(value);
        Account save = repository.save(account);
        return mapper.map(save, AccountDTO.class);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> getAllAccount = repository.findAll();
        return getAllAccount.stream().map((account) -> mapper.map(account, AccountDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Account isPresent = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not available")
        );
        repository.deleteById(id);
    }
}
