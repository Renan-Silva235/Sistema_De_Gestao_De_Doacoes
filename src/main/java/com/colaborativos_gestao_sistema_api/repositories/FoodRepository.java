package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
