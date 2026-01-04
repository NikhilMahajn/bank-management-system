package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bank.models.BankBranch;

@Repository
public interface BankBranchRepository extends JpaRepository<BankBranch,Long>{

}
