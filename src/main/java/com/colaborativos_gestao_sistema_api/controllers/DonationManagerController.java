package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import com.colaborativos_gestao_sistema_api.services.DonationManagerService;
import com.colaborativos_gestao_sistema_api.models.Food;
import com.colaborativos_gestao_sistema_api.models.Garment;
import com.colaborativos_gestao_sistema_api.models.Medicine;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/donation")
public class DonationManagerController {

    @Autowired
    private DonationManagerService donationManagerService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/store")
    public ResponseEntity<String> store(@RequestBody Map<String, Object> payload) {
        try {
            // O Controller apenas chama o Service. A "mágica" acontece lá dentro.
            donationManagerService.processFullDonation(payload);
            return ResponseEntity.ok("Doação registrada com sucesso!");
        } catch (Exception error) {
            return ResponseEntity.badRequest().body("Erro: " + error.getMessage());
        }
    }
}