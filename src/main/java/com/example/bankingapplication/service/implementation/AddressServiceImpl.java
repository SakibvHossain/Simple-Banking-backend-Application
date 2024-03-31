package com.example.bankingapplication.service.implementation;

import com.example.bankingapplication.dto.AccountDTO;
import com.example.bankingapplication.dto.AddressDTO;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.Address;
import com.example.bankingapplication.entity.User;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.repository.AddressRepository;
import com.example.bankingapplication.repository.UserRepository;
import com.example.bankingapplication.service.AddressService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    @Override
    public AddressDTO createAddress(AddressDTO dto, Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()){
            // Create the address entity from DTO
            Address address = mapper.map(dto, Address.class);

            // Set the address to the account
            Account account = optionalAccount.get();
            account.setAddress(address);

            //Get the userHolder from account and save the address
            User getUser = userRepository.findUserByUsername(account.getHolderName());
            getUser.setAddress(address);



            Address saveAddress = addressRepository.save(address);

            // Save the account, cascading to address if cascade type is set
            accountRepository.save(account);
            userRepository.save(getUser);

            // Return the address DTO
            return mapper.map(saveAddress, AddressDTO.class);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public AddressDTO updateAddress(AddressDTO dto, Long id) {
        Optional<Account> getAccount = accountRepository.findById(id);

        if (getAccount.isPresent()){
            // Create the address entity from DTO
            Address address = mapper.map(dto, Address.class);
            Address saveAddress = addressRepository.save(address);
            // Return the address DTO
            return mapper.map(saveAddress, AddressDTO.class);
        } else {
            throw new RuntimeException("Account not found");
        }
    }


    @Override
    public AddressDTO getAddressByID(String name) {
        Account activeAccountHolder = accountRepository.findAccountByHolderName(name);
        if(activeAccountHolder!=null){
            Address address = activeAccountHolder.getAddress();
            return mapper.map(address, AddressDTO.class);
        }else{
            throw new RuntimeException("Account holder not found");
        }
    }
}
