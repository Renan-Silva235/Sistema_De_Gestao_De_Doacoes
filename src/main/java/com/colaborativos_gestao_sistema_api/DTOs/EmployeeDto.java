package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Roles;
import com.colaborativos_gestao_sistema_api.models.Employee;

import java.time.LocalDate;

public record EmployeeDto(String name, String Cpf, LocalDate born, String email, Roles roles) {

    public EmployeeDto(Employee employee){
        this(employee.getNome(), employee.getCpf(), employee.getData_nascimento(), employee.getEmail(), employee.getCargo());
    }
}
