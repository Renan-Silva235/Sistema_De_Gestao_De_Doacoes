package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.models.Customer;
import com.colaborativos_gestao_sistema_api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("register-customer")
    public ResponseEntity<String> store(@RequestBody Customer customer){
        try{
            service.registerCustomer(customer);
            return ResponseEntity.ok("Cliente cadastrado com sucesso.");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Não foi possível cadastrar clinte: " + error);
        }
    }

    @GetMapping("/list-customers")
    public List<Customer> index(){
        return service.listCustomers();
    }



}
