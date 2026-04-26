package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.DTOs.DonorDTO;
import com.colaborativos_gestao_sistema_api.models.Donor;
import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.services.DonorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor")
public class DonorController {

    @Autowired
    private DonorService service;

    @PostMapping("/store")
    public ResponseEntity<String> store(
            @RequestBody @Valid Donor donor,
            @AuthenticationPrincipal Employee employeeLogin
    ){
        service.registerDonor(donor, employeeLogin.getId());
        return ResponseEntity.ok("Doador cadastrado com sucesso.");
    }

    @GetMapping("/index")
    public List<DonorDTO> index(){
        return service.listAllDonors();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid DonorDTO dto){
        service.updateDonor(id, dto);
        return ResponseEntity.ok("Cliente Atualizado com sucesso.");
    }

    @GetMapping("/show")
    public ResponseEntity<DonorDTO> show(@RequestParam String cpf) {
        // No seu Service, o método searchDonor precisaria retornar um DonorDTO
        DonorDTO donor = service.searchDonor(cpf);
        return ResponseEntity.ok(donor);
    }

}
