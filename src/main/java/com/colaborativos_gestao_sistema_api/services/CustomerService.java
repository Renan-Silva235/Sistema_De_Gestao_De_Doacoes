package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.models.Customer;
import com.colaborativos_gestao_sistema_api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer registerCustomer(Customer customer){
        return repository.save(customer);
    }

    public List<Customer> listCustomers(){
        return repository.findAll();
    }
}
