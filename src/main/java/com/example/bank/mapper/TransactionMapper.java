package com.example.bank.mapper;

import org.springframework.stereotype.Component;

import com.example.bank.dto.TransactionDto;
import com.example.bank.models.Account;
import com.example.bank.models.Transaction;
import com.example.bank.models.TransactionType;

@Component
public class TransactionMapper {
	public TransactionDto toDto(Transaction transaction,Account currentAccount){
		TransactionDto transactionDto = new TransactionDto();

		transactionDto.setSenderAccount(transaction.getSenderAccount().getAccountNumber());
		transactionDto.setSenderEmail(transaction.getSenderAccount().getCustomer().getEmail());
		transactionDto.setRecieverAccount(transaction.getRecieverAccount().getAccountNumber());
		transactionDto.setRecieverEmail(transaction.getRecieverAccount().getCustomer().getEmail());
		transactionDto.setAmount(transaction.getAmount());
		transactionDto.setType(
			transaction.getSenderAccount().equals(currentAccount)
				? TransactionType.DEBIT
				: TransactionType.CREDIT
		);

		return transactionDto;
	}
}
