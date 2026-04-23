package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.models.*;
import com.colaborativos_gestao_sistema_api.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    private ObjectMapper objectMapper;

    @Transactional
    public void processFullDonation(Map<String, Object> payload) {
        // 1. Identificar Funcionário Logado via Contexto de Segurança
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

        String categoria = payload.getOrDefault("categoria", "").toString();

        payload.remove("categoria");
        payload.remove("donorCpf");

        if ("ALIMENTICIO".equalsIgnoreCase(categoria) || "ALIMENTICIOS".equalsIgnoreCase(categoria)) {
            Food food = objectMapper.convertValue(payload, Food.class);
            food.setEmployee(employee);
            food.setDonor(donor);
            saveOrUpdateFood(food);

        } else if ("MEDICAMENTO".equalsIgnoreCase(categoria)) {
            Medicine medicine = objectMapper.convertValue(payload, Medicine.class);
            medicine.setEmployee(employee);
            medicine.setDonor(donor);
            saveOrUpdateMedicine(medicine);

        } else if ("VESTUARIO".equalsIgnoreCase(categoria)) {
            Garment garment = objectMapper.convertValue(payload, Garment.class);
            garment.setEmployee(employee);
            garment.setDonor(donor);
            saveOrUpdateGarment(garment);

        } else {
            throw new IllegalArgumentException("Categoria '" + categoria + "' inválida ou não suportada.");
        }
    }

    private void saveOrUpdateFood(Food food) {
        foodRepository.findByAlimentoAndPesoAndValidade(
                        food.getAlimento(), food.getPeso(), food.getValidade())
                .ifPresentOrElse(
                        existing -> {
                            existing.setQuantidade(existing.getQuantidade() + food.getQuantidade());
                            foodRepository.save(existing);
                        },
                        () -> foodRepository.save(food)
                );
    }

    private void saveOrUpdateMedicine(Medicine medicine) {
        medicineRepository.findByMedicamentoAndDosagemAndValidade(
                        medicine.getMedicamento(), medicine.getDosagem(), medicine.getValidade())
                .ifPresentOrElse(
                        existing -> {
                            existing.setQuantidade(existing.getQuantidade() + medicine.getQuantidade());
                            medicineRepository.save(existing);
                        },
                        () -> medicineRepository.save(medicine)
                );
    }

    private void saveOrUpdateGarment(Garment garment) {
        garmentRepository.findByProdutoAndMarcaAndTamanhoAndCor(
                        garment.getProduto(), garment.getMarca(), garment.getTamanho(), garment.getCor())
                .ifPresentOrElse(
                        existing -> {
                            existing.setQuantidade(existing.getQuantidade() + garment.getQuantidade());
                            garmentRepository.save(existing);
                        },
                        () -> garmentRepository.save(garment)
                );
    }
}