package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Categories;
import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Medicine;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MedicineDTO(Categories category,
                          String medicine,
                          String dosage,
                          LocalDate exceptionDate,
                          Integer amount,
                          String employee,
                          String donor,
                          Status status,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt
                          ) {

    public MedicineDTO(Medicine medicine){
        this(medicine.getCategoria(),
                medicine.getMedicamento(),
                medicine.getDosagem(),
                medicine.getValidade(),
                medicine.getQuantidade(),
                medicine.getEmployee().getNome(),
                medicine.getDonor().getNome(),
                medicine.getStatus(),
                medicine.getCreatedAt(),
                medicine.getUpdatedAt()
                );
    }

}
