package com.nava.cooperfilmes_back.dto;

import com.nava.cooperfilmes_back.domain.user.Role;

import java.util.Set;

public record LoginResponseDTO(String email, String name, String token, String role) {
}
