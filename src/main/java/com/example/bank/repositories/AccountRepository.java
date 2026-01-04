package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.models.Account;
import com.example.bank.models.Customer;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long>{
	Optional<Account> findByCustomer(Customer customer);
}
