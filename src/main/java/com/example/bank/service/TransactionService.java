package com.example.bank.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.bank.dto.TransactionDto;
import com.example.bank.mapper.TransactionMapper;
import com.example.bank.models.Account;
import com.example.bank.models.Transaction;

import com.example.bank.repositories.TransactionRepository;

@Service
public class TransactionService {
	
	private TransactionRepository transactionRepository;

	private AccountService accountService;

	private TransactionMapper transactionMapper;

	public TransactionService(TransactionRepository transactionRepository,
				AccountService accountService,
				TransactionMapper transactionMapper			
			){
		this.transactionRepository = transactionRepository;
		this.accountService = accountService;
		this.transactionMapper = transactionMapper;
	}


	public List<TransactionDto> getMyTransactions(){
		Account account = accountService.getAccountByToken();
		List<Transaction> transactions = transactionRepository.findBySenderAccountOrRecieverAccount(account, account);
		return transactions.stream().map(tx -> transactionMapper.toDto(tx,account)).toList();
	}

}
