package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.DTOs.EmployeeDto;
import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee) throws ResponseStatusException {
        if(repository.findByEmail(employee.getEmail()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado no sistema");
        if(repository.findByCpf(employee.getCpf()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já cadastrado no sistema.");
        String hashPassword = passwordEncoder.encode(employee.getPassword());
        employee.setSenha(hashPassword);
        return repository.save(employee);
    }

    public List<EmployeeDto> listEmployees(){
        return repository.findAll().stream().map(EmployeeDto::new).toList();
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado."));
        if(dto.name() != null) employee.setNome(dto.name());
        if(dto.Cpf() != null) employee.setCpf(dto.Cpf());
        if (dto.email() != null) employee.setEmail(dto.email());
        if (dto.born() != null) employee.setData_nascimento(dto.born());
        Employee updatedEntity = repository.save(employee);
        return new EmployeeDto(updatedEntity);
    }

    public void deleteEmployee(Long id) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado.");
        repository.deleteById(id);
    }
}

