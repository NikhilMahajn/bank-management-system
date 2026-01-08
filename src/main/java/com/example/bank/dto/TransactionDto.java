package com.example.bank.dto;

import com.example.bank.models.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransactionDto {
	public String senderEmail;

	public String senderAccount;

	public String recieverEmail;

	public String recieverAccount;

	public Long amount;

	public TransactionType type;
}
