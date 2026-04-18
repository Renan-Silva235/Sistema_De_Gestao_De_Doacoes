package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Food;
import com.colaborativos_gestao_sistema_api.models.Garment;
import com.colaborativos_gestao_sistema_api.models.Medicine;
import com.colaborativos_gestao_sistema_api.repositories.FoodRepository;
import com.colaborativos_gestao_sistema_api.repositories.GarmentRepository;
import com.colaborativos_gestao_sistema_api.repositories.MedicineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired private FoodRepository foodRepository;
    @Autowired private MedicineRepository medicineRepository;
    @Autowired
    private GarmentRepository garmentRepository;

    @Transactional
    public void distributeProduct(Long id, String category, int quantityToDistribute) {
        if (quantityToDistribute <= 0) {
            throw new IllegalArgumentException("A quantidade a ser distribuída deve ser maior que zero.");
        }

        if (category.equalsIgnoreCase("food")) {
            Food food = foodRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Alimento não encontrado."));

            if (food.getQuantidade() < quantityToDistribute) {
                throw new IllegalArgumentException("Estoque insuficiente! Disponível: " + food.getQuantidade());
            }

            food.setQuantidade(food.getQuantidade() - quantityToDistribute);

            if (food.getQuantidade() == 0) {
                food.setStatus(Status.INATIVO);
            }

            foodRepository.save(food);
        }

        else if (category.equalsIgnoreCase("garment")) {
            Garment garment = garmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

            if (garment.getQuantidade() < quantityToDistribute) {
                throw new IllegalArgumentException("Estoque insuficiente! Disponível: " + garment.getQuantidade());
            }

            garment.setQuantidade(garment.getQuantidade() - quantityToDistribute);

            if (garment.getQuantidade() == 0) {
                garment.setStatus(Status.INATIVO);
            }

            garmentRepository.save(garment);
        }

        else if (category.equalsIgnoreCase("medicine")) {
            Medicine medicine = medicineRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));

            if (medicine.getQuantidade() < quantityToDistribute) {
                throw new IllegalArgumentException("Estoque insuficiente! Disponível: " + medicine.getQuantidade());
            }

            medicine.setQuantidade(medicine.getQuantidade() - quantityToDistribute);

            if (medicine.getQuantidade() == 0) {
                medicine.setStatus(Status.INATIVO);
            }

            medicineRepository.save(medicine);
        }
    }
}
