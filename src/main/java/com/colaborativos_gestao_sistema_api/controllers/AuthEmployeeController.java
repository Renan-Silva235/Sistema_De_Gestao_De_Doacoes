package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login-employee")
public class AuthEmployeeController {

    @Autowired
    private EmployeeRepository repository;


}
