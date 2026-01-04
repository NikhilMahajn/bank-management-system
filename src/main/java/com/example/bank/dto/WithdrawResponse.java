package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WithdrawResponse {
	private String accountNumber;

	private Long balance;

	private Long withdrawAmount;


}
