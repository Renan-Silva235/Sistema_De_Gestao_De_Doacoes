package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.DTOs.CustomerDto;
import com.colaborativos_gestao_sistema_api.models.Customer;
import com.colaborativos_gestao_sistema_api.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(Customer customer){
        if(repository.findByEmail(customer.getEmail()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        if(repository.findByCpf(customer.getCpf()).isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já cadastrado");
        String hashPassword = passwordEncoder.encode(customer.getPassword());
        customer.setSenha(hashPassword);
        return repository.save(customer);
    }

    public List<CustomerDto> listCustomer(){
        return repository.findAll().stream().map(CustomerDto::new).toList();
    }

    public CustomerDto updateCustomer(Long id, CustomerDto dto){
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if(dto.name() != null) customer.setNome(dto.name());
        if(dto.born() != null) customer.setData_nascimento(dto.born());
        if(dto.email() != null) customer.setEmail(dto.email());
        if(dto.city() != null) customer.setCidade(dto.city());
        if(dto.state() != null) customer.setEstado(dto.state());

        Customer updatedCustomer = repository.save(customer);
        return new CustomerDto(updatedCustomer);
    }

    public void deleteCustomer(Long id){
        if(!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        repository.deleteById(id);
    }

}
