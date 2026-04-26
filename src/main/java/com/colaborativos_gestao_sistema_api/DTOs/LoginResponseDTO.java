package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Roles;

public record LoginResponseDTO(String username, String role, String token) {}
