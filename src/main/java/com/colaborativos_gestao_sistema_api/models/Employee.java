package com.colaborativos_gestao_sistema_api.models;

import com.colaborativos_gestao_sistema_api.enums.Roles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Funcionarios")
public class Employee extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles cargo;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // O nível de acesso vem do cargo específico do funcionário
        return List.of(new SimpleGrantedAuthority("ROLE_" + cargo.name()));
    }


    public Long getId() {
        return id;
    }

    public Roles getCargo() {
        return cargo;
    }

    public void setCargo(Roles cargo) {
        this.cargo = cargo;
    }
}