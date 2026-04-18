package com.colaborativos_gestao_sistema_api.repositories;

import com.colaborativos_gestao_sistema_api.models.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByEmail(String email);
    Optional<Donor> findByCpf(String cpf);
}
