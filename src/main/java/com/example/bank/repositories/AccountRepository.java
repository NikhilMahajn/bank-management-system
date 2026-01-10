package com.example.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bank.models.Account;
import com.example.bank.models.Customer;

import jakarta.persistence.LockModeType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account,Long>{
	Optional<Account> findByCustomer(Customer customer);
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Account> findByAccountNumber(String accountNumber);


	long countByIsActiveTrue();

	@Query("""
		SELECT COALESCE(SUM(a.balance), 0)
		FROM Account a
		WHERE a.isActive = :isActive
	""")
	long sumBalanceByIsActive(@Param("isActive") Boolean isActive);

	boolean existsByAccountNumber(String account);

	@Query("""
		SELECT 
			COUNT(a.id),
			SUM(CASE WHEN a.isActive = true THEN 1 ELSE 0 END),
			COALESCE(SUM(a.balance), 0)
		FROM Account a
		WHERE a.bankBranch.id = :branchId
	""")
	List<Object[]> getStatsForBranch(@Param("branchId") Long branchId);

}
