package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee){
        String hashPassword = passwordEncoder.encode(employee.getPassword());
        employee.setSenha(hashPassword);
        return repository.save(employee);
    }

    public List<Employee> listEmployees(){
        return repository.findAll();
    }
}
