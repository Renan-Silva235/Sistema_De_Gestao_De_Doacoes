package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<Medicine, Long>{
    Optional<Medicine> findByMedicamentoAndDosagemAndValidade(String medicine, String Dosage, LocalDate exceptionDate);
    List<Medicine> findByMedicamentoContainingIgnoreCaseAndStatus(String nome, Status status);
    long countByStatus(Status status);
    List<Medicine> findByValidadeBeforeAndStatus(LocalDate date, Status status);
}
