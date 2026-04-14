package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.DTOs.CustomerDto;
import com.colaborativos_gestao_sistema_api.models.Customer;
import com.colaborativos_gestao_sistema_api.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/store")
    public ResponseEntity<String> store(@RequestBody Customer customer) {
        service.registerCustomer(customer);
        return ResponseEntity.ok("Cliente cadastrado com sucesso");
    }

    @GetMapping("/index")
    public List<CustomerDto> index(){
        return service.listCustomer();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid CustomerDto dto){
        CustomerDto updated = service.updateCustomer(id, dto);
        return ResponseEntity.ok("Cliente atualizado");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteCustomer(id);
        return ResponseEntity.ok("Cliente deletado com sucesso");
    }




}
