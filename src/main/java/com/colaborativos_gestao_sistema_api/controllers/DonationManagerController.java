package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import com.colaborativos_gestao_sistema_api.services.DonationManagerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/donation")
public class DonationManagerController {

    @Autowired
    private DonationManagerService donationManagerService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/store", consumes = {"multipart/form-data"})
    public ResponseEntity<String> store(
            @RequestPart("dados") String dadosJson,
            @RequestPart(value = "arquivo", required = false) MultipartFile arquivo) {
        try {
            Map<String, Object> payload = objectMapper.readValue(dadosJson, Map.class);
            donationManagerService.processFullDonationWithImage(payload, arquivo);
            return ResponseEntity.ok("Doação registrada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao processar: " + e.getMessage());
        }
    }
}