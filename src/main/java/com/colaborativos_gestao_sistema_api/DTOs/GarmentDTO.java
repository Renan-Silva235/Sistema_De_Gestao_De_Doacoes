package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.enums.Categories;
import com.colaborativos_gestao_sistema_api.enums.Status;
import com.colaborativos_gestao_sistema_api.models.Garment;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record GarmentDTO(Categories category,
                          String product,
                          String typeProduct,
                          String brand,
                          String size,
                          String color,
                          Integer amount,
                          String employee,
                          String donor,
                          Status status,
                          LocalDateTime createdAt,
                         LocalDateTime updatedAt
    ) {

        public GarmentDTO(Garment garment){
            this(garment.getCategoria(),
                    garment.getProduto(),
                    garment.getTipo_produto(),
                    garment.getMarca(),
                    garment.getTamanho(),
                    garment.getCor(),
                    garment.getQuantidade(),
                    garment.getEmployee().getNome(),
                    garment.getDonor().getNome(),
                    garment.getStatus(),
                    garment.getCreatedAt(),
                    garment.getUpdatedAt()
            );
        }
}
