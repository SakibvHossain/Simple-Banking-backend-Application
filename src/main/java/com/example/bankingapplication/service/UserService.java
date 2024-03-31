package com.example.bankingapplication.service;

import com.example.bankingapplication.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserDetailsByName(String name);
}
