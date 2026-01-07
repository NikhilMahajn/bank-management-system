package com.example.bank.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bank.models.Role;
import com.example.bank.models.User;
import com.example.bank.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;


    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public Map<String,String> setupAdmin(){

		if (userRepository.existsByRole(Role.ROLE_ADMIN)) {
			throw new AccessDeniedException("Admin already exists");
		}
        User user = new User();
        user.setActive(true);
        user.setUsername(adminUsername);
        user.setPassword(passwordEncoder.encode(adminPassword));
        user.setRole(Role.ROLE_ADMIN);

        userRepository.save(user);

        Map<String,String> response = new HashMap<>();
        response.put("Status","Admin user created");
        return response;
        
	}
}

