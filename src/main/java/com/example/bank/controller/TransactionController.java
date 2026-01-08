package com.example.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.TransactionDto;
import com.example.bank.repositories.TransactionRepository;
import com.example.bank.service.TransactionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	private TransactionService transactionService;

	public TransactionController(TransactionService transactionService){
		this.transactionService = transactionService;
	}

	@GetMapping("/my-transactions")
	public List<TransactionDto> getMyTransactionsHandler() {
		return transactionService.getMyTransactions();
	}
	
}
