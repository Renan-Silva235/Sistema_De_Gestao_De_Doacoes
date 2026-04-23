package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.models.*;
import com.colaborativos_gestao_sistema_api.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class DonationManagerService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void processFullDonationWithImage(Map<String, Object> payload, MultipartFile arquivo) throws IOException {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Funcionário logado não encontrado: " + email));

        if (!payload.containsKey("donorCpf")) {
            throw new IllegalArgumentException("O campo 'donorCpf' é obrigatório.");
        }
        String cpf = payload.get("donorCpf").toString();
        Donor donor = donorRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com o CPF: " + cpf));

        int quantidadeDoada = Integer.parseInt(payload.get("quantidade").toString());
        donor.incrementTotal(donor.getTotalDoacoes() + quantidadeDoada);
        donorRepository.save(donor);

        String categoria = payload.getOrDefault("categoria", "").toString().toUpperCase();
        payload.remove("categoria");
        payload.remove("donorCpf");

        Object savedProduct = null;

        if (categoria.contains("ALIMENTICIO") || categoria.contains("ALIMENTICIOS")) {
            Food food = objectMapper.convertValue(payload, Food.class);
            food.setEmployee(employee);
            food.setDonor(donor);
            savedProduct = foodRepository.save(food);

        } else if (categoria.contains("MEDICAMENTO")) {
            Medicine medicine = objectMapper.convertValue(payload, Medicine.class);
            medicine.setEmployee(employee);
            medicine.setDonor(donor);
            savedProduct = medicineRepository.save(medicine);

        } else if (categoria.contains("VESTUARIO")) {
            Garment garment = objectMapper.convertValue(payload, Garment.class);
            garment.setEmployee(employee);
            garment.setDonor(donor);
            savedProduct = garmentRepository.save(garment);

        } else {
            throw new IllegalArgumentException("Categoria '" + categoria + "' inválida.");
        }

        if (arquivo != null && !arquivo.isEmpty() && savedProduct != null) {
            ProductImage img = new ProductImage();
            img.setArquivo(arquivo.getBytes());
            img.setNomeArquivo(arquivo.getOriginalFilename());
            img.setTipoArquivo(arquivo.getContentType());

            if (savedProduct instanceof Food f) {
                img.setFood(f);
            } else if (savedProduct instanceof Medicine m) {
                img.setMedicine(m);
            } else if (savedProduct instanceof Garment g) {
                img.setGarment(g);
            }

            productImageRepository.save(img);
        }
    }
}