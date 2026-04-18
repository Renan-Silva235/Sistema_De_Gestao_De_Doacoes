package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Categories;
import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Donor;
import com.colaborativos_gestao_sistema_api.models.Employee;
import com.colaborativos_gestao_sistema_api.models.Food;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FoodDTO(Categories category,
                      String food,
                      String weight,
                      LocalDate expirationDate,
                      Integer amount,
                      String employee,
                      String donor,
                      Status status,
                      LocalDateTime createdAt,
                      LocalDateTime updatedAt
                      ) {


    public FoodDTO(Food food){
        this(food.getCategoria(),
                food.getAlimento(),
                food.getPeso(),
                food.getValidade(),
                food.getQuantidade(),
                food.getEmployee().getNome(),
                food.getDonor().getNome(),
                food.getStatus(),
                food.getCreatedAt(),
                food.getUpdatedAt()
        );
    }
}
