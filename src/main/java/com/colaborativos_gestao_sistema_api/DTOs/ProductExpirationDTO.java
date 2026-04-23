package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Categories;

import java.time.LocalDate;

public record ProductExpirationDTO(Categories category, String productName, LocalDate expirationDate) {

}
