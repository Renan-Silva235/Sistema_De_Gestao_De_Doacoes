package com.colaborativos_gestao_sistema_api.controllers;

import com.colaborativos_gestao_sistema_api.DTOs.ProductExpirationDTO;
import com.colaborativos_gestao_sistema_api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/expiration")
    public List<ProductExpirationDTO> checkExpiration() {
        return productService.checkExpiration();
    }

    @GetMapping("/index")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(productService.listAllProducts());
    }

    @GetMapping("/show")
    public ResponseEntity<List<Object>> show(@RequestParam String name){
        List<Object> results = productService.searchProductByName(name);
        return ResponseEntity.ok(results);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Object updateData){
        try {
            Object updated =  productService.updateProduct(id, updateData);
            return ResponseEntity.ok(updated);
        }catch (RuntimeException error){
            return ResponseEntity.status(404).body(error.getMessage());
        }catch (Exception error){
            return ResponseEntity.badRequest().body("Erro ao atualizar" + error.getMessage());
        }
    }
}
