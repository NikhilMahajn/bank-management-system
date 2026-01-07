package com.example.bank.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.bank.dto.AuthDto;
import com.example.bank.dto.LoginRequest;
import com.example.bank.service.JwtService;
import com.example.bank.service.CustomUserDetailsService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public AuthDto login(@Valid @RequestBody LoginRequest request) {
        System.out.println(request.getUsername());
        System.out.println(request.getPassword());

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        AuthDto authDto = new AuthDto();
        authDto.setToken(jwtService.generateToken(request.getUsername()));
        authDto.setStatus(HttpStatus.OK.value());
        return authDto;
    }

    @GetMapping("/admin/set-admin")
    public Map<String,String> setAdmin() {
        return customUserDetailsService.setupAdmin();
    }
    
}
