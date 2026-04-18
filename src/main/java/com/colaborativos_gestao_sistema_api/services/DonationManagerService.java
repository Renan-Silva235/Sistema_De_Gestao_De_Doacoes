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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonationManagerService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    @Transactional
    public void processDonation(Object donation){
        if(donation instanceof Food food){
            foodRepository.findByAlimentoAndPesoAndValidade(
                    food.getAlimento(),
                    food.getPeso(),
                    food.getValidade())
                    .ifPresentOrElse(
                            existing -> {
                                existing.setQuantidade(existing.getQuantidade() + food.getQuantidade());
                                foodRepository.save(existing);
                            },
                            () -> {
                                foodRepository.save(food);
                            });
        } else if (donation instanceof Medicine medicine) {
            medicineRepository.findByMedicamentoAndDosagemAndValidade(
                    medicine.getMedicamento(),
                    medicine.getDosagem(),
                    medicine.getValidade()
            ).ifPresentOrElse(
                    existing -> {
                        existing.setQuantidade(existing.getQuantidade() + medicine.getQuantidade());
                        medicineRepository.save(existing);
                    },
                    () -> {
                        medicineRepository.save(medicine);
                    }
            );

        } else if (donation instanceof Garment garment) {
            garmentRepository.findByProdutoAndMarcaAndTamanhoAndCor(
                    garment.getProduto(),
                    garment.getMarca(),
                    garment.getTamanho(),
                    garment.getCor()
            ).ifPresentOrElse(
                    existing -> {
                        existing.setQuantidade(existing.getQuantidade() + garment.getQuantidade());
                        garmentRepository.save(existing);
                    },
                    () -> {
                        garmentRepository.save(garment);
                    }
            );

        }else{
            throw new IllegalArgumentException("Tipo de doação inválido ou não suportado.");
        }
    }

    public Map<String, List<?>> listAllProducts(){
        Map<String, List<?>> allProducts = new HashMap<>();

        allProducts.put("foods", foodRepository.findAll());
        allProducts.put("garments", garmentRepository.findAll());
        allProducts.put("medicines", medicineRepository.findAll());
        return allProducts;
    }

    public List<Object> searchProductByName(String name){
        List<Object> products = new ArrayList<>();
        products.addAll(foodRepository.findByAlimentoContainingIgnoreCaseAndStatus(name, Status.ATIVO));
        products.addAll(garmentRepository.findByProdutoContainingIgnoreCaseAndStatus(name, Status.ATIVO));
        products.addAll(medicineRepository.findByMedicamentoContainingIgnoreCaseAndStatus(name, Status.ATIVO));

        return products;
    }

    @Transactional
    public Object updateProduct(Long id, Object updateData) {
        if (updateData instanceof Food foodUpdate) {
            return foodRepository.findById(id).map(existing -> {
                if (foodUpdate.getAlimento() != null) existing.setAlimento(foodUpdate.getAlimento());
                if (foodUpdate.getPeso() != null) existing.setPeso(foodUpdate.getPeso());
                if (foodUpdate.getValidade() != null) existing.setValidade(foodUpdate.getValidade());
                if (foodUpdate.getQuantidade() != 0) existing.setQuantidade(foodUpdate.getQuantidade());
                if (foodUpdate.getStatus() != null) existing.setStatus(foodUpdate.getStatus());
                return foodRepository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Comida não encontrada."));
        }

        if (updateData instanceof Garment garmentUpdate) {
            return garmentRepository.findById(id).map(existing -> {
                if (garmentUpdate.getProduto() != null) existing.setProduto(garmentUpdate.getProduto());
                if (garmentUpdate.getMarca() != null) existing.setMarca(garmentUpdate.getMarca());
                if (garmentUpdate.getTamanho() != null) existing.setTamanho(garmentUpdate.getTamanho());
                if (garmentUpdate.getCor() != null) existing.setCor(garmentUpdate.getCor());
                if (garmentUpdate.getQuantidade() != 0) existing.setQuantidade(garmentUpdate.getQuantidade());
                if (garmentUpdate.getStatus() != null) existing.setStatus(garmentUpdate.getStatus());
                return garmentRepository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Roupa não encontrada."));
        }

        if (updateData instanceof Medicine medicineUpdate){
            return medicineRepository.findById(id).map(existing -> {
                if (medicineUpdate.getMedicamento() != null) existing.setMedicamento(medicineUpdate.getMedicamento());
                if (medicineUpdate.getDosagem() != null) existing.setDosagem(medicineUpdate.getDosagem());
                if (medicineUpdate.getValidade() != null) existing.setValidade(medicineUpdate.getValidade());
                if (medicineUpdate.getQuantidade() != 0) existing.setQuantidade(medicineUpdate.getQuantidade());
                if (medicineUpdate.getStatus() != null) existing.setStatus(medicineUpdate.getStatus());
                return medicineRepository.save(existing);
            }).orElseThrow(() -> new RuntimeException("Medicamento não encontrado."));
        }
        throw new IllegalArgumentException("Categoria inválida");
    }

}




