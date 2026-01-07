package com.example.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse {
	private String status;

	private String message;

	public ApiResponse(String status,String message){
		this.status = status;
		this.message = message;
	}
}
