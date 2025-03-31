package com.nava.cooperfilmes_back.dto;

public record LoginResponseDTO(String email, String name, String token, String role) {
}
