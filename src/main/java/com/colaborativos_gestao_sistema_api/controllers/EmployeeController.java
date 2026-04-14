package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.DTOs.EmployeeDto;
import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.View;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @PostMapping("/store")
    public ResponseEntity<String> store(@RequestBody @Valid Employee employee){
            service.registerEmployee(employee);
            return ResponseEntity.ok("Funcionário Cadastrado com sucesso");
    }

    @GetMapping("/index")
    public List<EmployeeDto> index(){
        return service.listEmployees();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestBody @Valid EmployeeDto dto){
        EmployeeDto updated = service.updateEmployee(id, dto);
        return ResponseEntity.ok("atualização concluída");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteEmployee(id);
        return ResponseEntity.ok("Funcionário deletado com sucesso");
    }
}
