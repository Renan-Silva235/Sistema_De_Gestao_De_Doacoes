package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Garment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GarmentRepository extends JpaRepository<Garment, Long> {
    Optional<Garment> findByProdutoAndMarcaAndTamanhoAndCor(String product, String brand, String size, String color);
    List<Garment> findByProdutoContainingIgnoreCaseAndStatus(String nome, Status status);
    long countByStatus(Status status);
}
