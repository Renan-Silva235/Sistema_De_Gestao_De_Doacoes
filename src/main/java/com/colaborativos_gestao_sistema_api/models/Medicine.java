package com.colaborativos_gestao_sistema_api.models;

import com.colaborativos_gestao_sistema_api.enums.Categories;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "Medicamentos")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Categories categoria = Categories.MEDICAMENTOS;

    @Size(max = 120, message = "Campo medicamento deve no máximo 120 caracteres.")
    @NotBlank(message = "Campo medicamento inválido.")
    private String medicamento;

    @NotNull(message = "Campo dosagem é obrigatório.")
    private float dosagem;

    @NotBlank(message = "Campo validade é obrigatório.")
    private LocalDate validade;

    @NotNull(message = "Campo quantidade é obrigatório.")
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    public Long getId() {
        return id;
    }

    public Categories getCategoria() {
        return categoria;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public float getDosagem() {
        return dosagem;
    }

    public void setDosagem(float dosagem) {
        this.dosagem = dosagem;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

}
