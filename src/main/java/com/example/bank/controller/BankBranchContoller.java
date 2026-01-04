package com.example.bank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.bank.dto.BankBranchDto;
import com.example.bank.repositories.BankBranchRepository;
import com.example.bank.service.BankBranchService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/branch")
public class BankBranchContoller {

	
	private final BankBranchService bankBranchService;

	public BankBranchContoller(BankBranchService bankBranchService){
		this.bankBranchService = bankBranchService;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create-branch")
	public BankBranchDto creaetBranchHandler(@Valid @RequestBody BankBranchDto branchDto) {
		
		return bankBranchService.creaetBranch(branchDto);
	}
	

}
