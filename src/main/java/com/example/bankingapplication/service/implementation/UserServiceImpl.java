package com.example.bankingapplication.service.implementation;

import com.example.bankingapplication.dto.UserDTO;
import com.example.bankingapplication.entity.User;
import com.example.bankingapplication.repository.UserRepository;
import com.example.bankingapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper mapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User dtoToEntity = mapper.map(userDTO, User.class);
        User save = repository.save(dtoToEntity);
        return mapper.map(save, UserDTO.class);
    }

    @Override
    public UserDTO getUserDetailsByName(String name) {
        User detailOfUser = repository.findUserByUsername(name);
        if (detailOfUser != null){
            return mapper.map(detailOfUser, UserDTO.class);
        }else {
            throw new RuntimeException("User not found by name: "+name);
        }
    }


}
