package com.example.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.models.Account;
import com.example.bank.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>{
	List<Transaction> findBySenderAccountOrRecieverAccount(Account sender, Account receiver);

}
