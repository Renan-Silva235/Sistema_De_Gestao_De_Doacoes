package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.models.Garment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarmentRepository extends JpaRepository<Garment, Long> {
}
