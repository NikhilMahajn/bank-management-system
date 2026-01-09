package com.example.bank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.models.BankBranch;
import java.util.List;


@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch,Long>{
	Optional<BankBranch> findByBranchCode(String branchCode);
}
