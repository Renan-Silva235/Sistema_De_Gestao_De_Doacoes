package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping("/register-employee")
    public ResponseEntity<String> store(@RequestBody @Valid Employee employee){
        try{
            service.registerEmployee(employee);
            return ResponseEntity.ok("Funcionário Cadastrado com sucesso");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Erro para cadastrar: " + error);
        }
    }

    @GetMapping("list-employees")
    public List<Employee> index(){
        return service.listEmployees();
    }

}
