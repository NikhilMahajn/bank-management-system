package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TransferDto {
	private String recieverAccount;

	private Long amount;
}
