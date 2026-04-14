package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Roles;
import com.colaborativos_gestao_sistema_api.models.Customer;

import java.time.LocalDate;

public record CustomerDto(String name,
                          String Cpf,
                          LocalDate born,
                          String email,
                          String city,
                          String state) {

    public CustomerDto(Customer customer){
        this(customer.getNome(),
                customer.getCpf(),
                customer.getData_nascimento(),
                customer.getEmail(),
                customer.getCidade(),
                customer.getEstado());
    }
}
