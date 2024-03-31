package com.example.bankingapplication.service.implementation;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.User;
import com.example.bankingapplication.exception.AccountHolderNotFound;
import com.example.bankingapplication.exception.HolderAlreadyExist;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.repository.UserRepository;
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
    private UserRepository userRepository;
    private ModelMapper mapper;
    @Override
    public AccountDTO addAccount(AccountDTO dto) {
        Account getInfo = repository.findAccountByHolderName(dto.getHolderName());
        if(getInfo != null){
            throw new HolderAlreadyExist("Account holder already exist with name"+dto.getHolderName());
        }
        Account storeDtoToAccount = mapper.map(dto, Account.class);
        Account saveData = repository.save(storeDtoToAccount);

        //Getting the user by accountHolderName
        User getUser = userRepository.findUserByUsername(saveData.getHolderName());
        //set account whatever the user have
        getUser.setAccount(saveData);
        //save it
        userRepository.save(getUser);

//      AccountDTO storeAccountToDTO = mapper.map(saveData, AccountDTO.class);
        return mapper.map(saveData, AccountDTO.class);
    }

    @Override
    public Account getAccount(String name) {
        Account account = repository.findAccountByHolderName(name);
        if(account == null || account.getHolderName() == null){
            throw new AccountHolderNotFound("Account holder "+name+" Not found");
        }
        //Account getTheAccount = repository.findAccountByHolderName(name);
        //mapper.map(getTheAccount, AccountDTO.class);
        return repository.findAccountByHolderName(name);
    }

    @Override
    public Account deposit(Long id, double balance) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountHolderNotFound("Account holder not found with id: "+id));
        double value = account.getBalance() + balance;
        account.setBalance(value);

        return repository.save(account);
    }

    @Override
    public AccountDTO withdraw(Long id, double balance) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountHolderNotFound("Account holder not found with given id: "+id));
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
        try {
            List<Account> getAllAccount = repository.findAll();
            return getAllAccount.stream()
                    .map(account -> mapper.map(account, AccountDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new AccountHolderNotFound("Not account found");
        }

    }

    @Override
    public void delete(Long id) {
        Account isPresent = repository.findById(id).orElseThrow(
                () -> new AccountHolderNotFound("Holder not found with a given id: "+id)
        );
        repository.deleteById(id);
    }
}
