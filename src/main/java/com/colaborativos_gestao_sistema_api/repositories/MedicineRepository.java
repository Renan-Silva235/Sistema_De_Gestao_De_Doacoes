package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
}
