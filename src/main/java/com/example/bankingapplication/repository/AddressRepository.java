package com.example.bankingapplication.repository;

import com.example.bankingapplication.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
