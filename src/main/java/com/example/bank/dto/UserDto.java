package com.example.bank.dto;

import com.example.bank.models.Role;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class UserDto {

	private Long id;

    private String username;

    private Role role;

    private boolean active = true;

    private Long referenceId; 
}
