package com.example.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bank.dto.BankBranchDto;
import com.example.bank.exceptions.ResourceNotFoundException;
import com.example.bank.mapper.BankBranchMapper;
import com.example.bank.models.BankBranch;
import com.example.bank.models.Employee;
import com.example.bank.repositories.BankBranchRepository;

import jakarta.transaction.Transactional;

@Service
public class BankBranchService {

	private final BankBranchRepository bankBranchRepository;

	private final BankBranchMapper bankBranchMapper;

	public BankBranchService(BankBranchRepository bankBranchRepository,BankBranchMapper bankBranchMapper){
		this.bankBranchRepository = bankBranchRepository;
		this.bankBranchMapper = bankBranchMapper;
	}
	
	@Transactional
	public BankBranchDto creaetBranch(BankBranchDto branchDto){
		BankBranch bankBranch = bankBranchMapper.toEntity(branchDto);
		bankBranch.setBranchCode("Default");
		bankBranchRepository.save(bankBranch);

		String branchCode = generateBranchCode(bankBranch.getId());
		bankBranch.setBranchCode(branchCode);

		bankBranchRepository.save(bankBranch);

		return bankBranchMapper.toDto(bankBranch);
	}


	private String generateBranchCode(Long id) {
        return "BANK" + String.format("%06d", id);
    }

	@Transactional
    public void addEmployeeToBranch(String branchCode, Employee employee) {

        BankBranch branch = bankBranchRepository.findByBranchCode(branchCode)
            .orElseThrow(() ->
                new ResourceNotFoundException("Branch not found"));

        branch.addEmployee(employee);
		employee.setBankBranch(branch);

    }

	public List<BankBranchDto> getAllBranches(){
		List<BankBranch> branches = bankBranchRepository.findAll().stream().toList();
		return branches.stream().map(bankBranchMapper::toDto).toList();
	}

}
