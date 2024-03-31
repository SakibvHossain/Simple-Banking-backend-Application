package com.example.bankingapplication.service;

import com.example.bankingapplication.dto.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO dto, Long id);
    AddressDTO updateAddress(AddressDTO dto, Long id);
    AddressDTO getAddressByID(String name);
}
