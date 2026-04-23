package com.colaborativos_gestao_sistema_api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "produto_imagens")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "arquivo", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] arquivo;

    @Column(name = "nome_arquivo")
    private String nomeArquivo;

    @Column(name = "tipo_arquivo")
    private String tipoArquivo;

    @OneToOne
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    @OneToOne
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;

    @OneToOne
    @JoinColumn(name = "garment_id", referencedColumnName = "id")
    private Garment garment;

    public ProductImage() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Garment getGarment() {
        return garment;
    }

    public void setGarment(Garment garment) {
        this.garment = garment;
    }
}