package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByAlimentoAndPesoAndValidade(String food, String weight, LocalDate expirationDAte);
    List<Food> findByAlimentoContainingIgnoreCaseAndStatus(String nome, Status status);
    long countByStatus(Status status);
    List<Food> findByValidadeBeforeAndStatus(LocalDate date, Status status);
    List<Food> findByValidadeBetween(LocalDate start, LocalDate end);
}
