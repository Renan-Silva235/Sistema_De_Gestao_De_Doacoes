package com.colaborativos_gestao_sistema_api.services;

import com.colaborativos_gestao_sistema_api.DTOs.DonorDTO;
import com.colaborativos_gestao_sistema_api.models.Donor;
import com.colaborativos_gestao_sistema_api.repositories.DonorRepository;
import com.colaborativos_gestao_sistema_api.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository repository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Donor registerDonor(Donor donor, Long employeeId) { // Receba o ID do funcionário
        if (repository.findByEmail(donor.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado no sistema");
        }
        if (repository.findByCpf(donor.getCpf()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cpf já cadastrado no sistema");
        }

        var employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário responsável não encontrado"));

        donor.setEmployee(employee);

        return repository.save(donor);
    }
    public List<DonorDTO> listAllDonors(){
        return repository.findAll().stream().map(DonorDTO::new).toList();
    }

    public DonorDTO updateDonor(Long id, DonorDTO dto){
        Donor donor = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador não encontrado"));

        if (dto.name() != null) donor.setNome(dto.name());
        if (dto.cpf() != null) donor.setCpf(dto.cpf());
        if (dto.born() != null) donor.setData_nascimento(dto.born());
        if (dto.email() != null) donor.setEmail(dto.email());
        if (dto.city() != null) donor.setCidade(dto.city());
        if (dto.state() != null) donor.setEstado(dto.state());

        Donor updatedDonor = repository.save(donor);
        return new DonorDTO(updatedDonor);
    }

    public DonorDTO searchDonor(String cpf) {
        return repository.findByCpf(cpf)
                .map(DonorDTO::new)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doador não encontrado com este CPF"));
    }

}
