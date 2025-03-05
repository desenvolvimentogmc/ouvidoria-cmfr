package br.com.gmc.ouvidoria.entity.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Classe que representa um funcionário
 * Possui um User (Usuário) de acesso ao sistema
 * Recomendado que seja identificado pelo e-mail
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see User
 * @see Department
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String employeeId; //matrícula ou funcional

    @Column(nullable = false, unique = true)
    private String cpf;

    private String createdBy; //nome do usuário que criou o usuário

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    private Department department;
    
}
