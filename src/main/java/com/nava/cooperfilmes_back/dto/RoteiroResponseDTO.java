package com.nava.cooperfilmes_back.dto;

public record RoteiroResponseDTO(
        Long id,
        String email,
        String name,
        String phoneNumber,
        String movieScript,
        String status
) {
}
