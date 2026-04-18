package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.services.DonationManagerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donation")
public class DonationManagerController {

    @Autowired
    private DonationManagerService donationManagerService;

    @PostMapping("/store")
    public ResponseEntity<String> store(@RequestBody @Valid Object donation){
        try{
            donationManagerService.processDonation(donation);
            return ResponseEntity.ok("Doação cadastrada com sucesso.");
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Erro ao cadastrar doação: " + error.getMessage());
        }
    }

    @GetMapping("/index")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(donationManagerService.listAllProducts());
    }

    @GetMapping("/show")
    public ResponseEntity<List<Object>> show(@RequestParam String name){
        List<Object> results = donationManagerService.searchProductByName(name);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Object updateData){
        try {
            Object updated =  donationManagerService.updateProduct(id, updateData);
            return ResponseEntity.ok(updated);
        }catch (RuntimeException error){
            return ResponseEntity.status(404).body(error.getMessage());
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Erro ao atualizar" + error.getMessage());
        }
    }
}
