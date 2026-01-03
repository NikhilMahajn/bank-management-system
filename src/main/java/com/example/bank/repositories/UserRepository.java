package com.example.bank.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bank.models.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByUsername(String username);
}
