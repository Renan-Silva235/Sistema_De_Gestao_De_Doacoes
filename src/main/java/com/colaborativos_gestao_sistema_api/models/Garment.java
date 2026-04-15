package com.colaborativos_gestao_sistema_api.models;
import com.colaborativos_gestao_sistema_api.enums.Categories;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "Vestuarios")
public class Garment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Categories categoria = Categories.VESTUARIOS;

    @Size(max = 120, message = "Campo produto deve no máximo 120 caracteres.")
    @NotBlank(message = "Campo produto do produto inválido.")
    private String produto;

    @NotBlank(message = "Campo tipo produto é obrigatório")
    private String tipo_produto;

    @NotBlank(message = "Campo marca é obrigatório.")
    @Size(max = 120, message = "Campo marca deve no máximo 120 caracteres.")
    private String marca;

    @NotNull(message = "Campo tamanho é obrigatório")
    private int tamanho;

    @NotBlank(message = "Campo marca é obrigatório.")
    @Size(max = 25, message = "Campo marca deve no máximo 25 caracteres.")
    private String cor;

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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(String tipo_produto) {
        this.tipo_produto = tipo_produto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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


