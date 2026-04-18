package com.colaborativos_gestao_sistema_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@MappedSuperclass
public abstract class User implements UserDetails {

    @NotBlank(message = "Campo Nome é obrigatório")
    @Size(min=3, max=70, message = "Campo Nome deve ter de 3 a 50 caracteres.")
    @Pattern(regexp = "^[\\p{L}\\s]+$", message = "Campo Nome inválido")
    private String nome;

    @NotBlank(message = "Campo CPF é obrigatório")
    @CPF(message = "CPF Inválido")
    @Size(max = 11, message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Campo data de nascimento é obrigatório.")
    @Past(message = "A data de nascimento deve ser uma data passada")
    private LocalDate data_nascimento;

    @Email(message = "E-mail inválido")
    @NotBlank
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Campo senha é obrigatório.")
    @Size(min = 6)
    @Column(length = 60, nullable = false) // MANTIDO EM 60 CONFORME VOCÊ QUERIA
    private String senha;

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public abstract Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
