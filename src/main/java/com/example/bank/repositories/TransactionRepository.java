package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	
}
