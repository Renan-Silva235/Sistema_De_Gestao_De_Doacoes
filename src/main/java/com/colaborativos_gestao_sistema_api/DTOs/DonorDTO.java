package com.colaborativos_gestao_sistema_api.DTOs;

import com.colaborativos_gestao_sistema_api.models.Donor;

import java.time.LocalDate;

public record DonorDTO(String name,
                       String cpf,
                       LocalDate born,
                       String email,
                       String city,
                       String state,
                       String employee,
                       Integer totalDonations
                       ) {

    public DonorDTO(Donor donor){
        this(donor.getNome(), donor.getCpf(),
                donor.getData_nascimento(),
                donor.getEmail(),
                donor.getCidade(),
                donor.getEstado(),
                donor.getEmployee().getNome(),
                donor.getTotalDoacoes()
        );
    }
}
