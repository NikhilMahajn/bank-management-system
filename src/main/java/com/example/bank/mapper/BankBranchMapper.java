package com.example.bank.mapper;

import org.springframework.stereotype.Component;

import com.example.bank.dto.BankBranchDto;
import com.example.bank.models.BankBranch;

@Component
public class BankBranchMapper {
	
	public BankBranchDto toDto(BankBranch bankBranch){
		BankBranchDto bankBranchDto = new BankBranchDto();
		bankBranchDto.setBranchName(bankBranch.getBranchName());
		bankBranchDto.setBranchCode(bankBranch.getBranchCode());
		bankBranchDto.setCity(bankBranch.getCity());
		bankBranchDto.setId(bankBranch.getId());
		return bankBranchDto;
	}

	public BankBranch toEntity(BankBranchDto bankBranchDto){
		BankBranch bankBranch = new BankBranch();
		bankBranch.setBranchName(bankBranchDto.getBranchName());
		bankBranch.setBranchCode(bankBranchDto.getBranchCode());
		bankBranch.setCity(bankBranchDto.getCity());
		return bankBranch;

	}
}
