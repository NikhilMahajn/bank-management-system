package com.example.bank.exceptions;

public class InsufficientBalanceException extends RuntimeException {
	public InsufficientBalanceException(String message){
		super(message);
	}
}
