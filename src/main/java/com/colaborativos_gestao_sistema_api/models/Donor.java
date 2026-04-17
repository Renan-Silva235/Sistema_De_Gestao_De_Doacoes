package com.colaborativos_gestao_sistema_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Donor {

    @NotBlank(message = "Campo Nome é obrigatório")
    @Size(min=3, max=70, message = "Campo Nome deve ter de 3 a 50 caracteres.")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Campo Nome inválido")
    private String nome;

    @NotBlank(message = "Campo CPF é obrigatório")
    @CPF(message = "CPF Inválido")
    @Size(max = 11, message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Campo data de nascimento é obrigatório.")
    private LocalDate data_nascimento;

    @Email(message = "E-mail inválido")
    @NotBlank
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Campo Cidade é obrigatório")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "Campo Estado é obrigatório")
    @Column(nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private int totalDoacoes = 0;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void incrementTotal(int quantidade) {
        this.totalDoacoes += quantidade;
    }

}
