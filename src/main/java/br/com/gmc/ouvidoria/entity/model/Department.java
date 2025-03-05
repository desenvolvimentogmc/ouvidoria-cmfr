package br.com.gmc.ouvidoria.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Classe que representa um Departamento
 * Os departamentos são usados para cadastrar tipos de demandas e funcionários
 * Os funcionários com nível ADMIN recebe chamados de acordo com seu departamento
 * Os tipos de chamados serão encaminhadas para o Departamento
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "tb_departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "Nome não pode ser nulo.")
    @NotBlank(message = "Nome não pode estar em branco.")
    @NotEmpty(message = "Nome nção pode estar vazio.")
    private String name;

    @Email(message = "E-mail inválido.")
    private String email;

    @Column(nullable = false, length = 15)
    @NotNull(message = "Telefone não pode ser nulo")
    private String phone;

    @Column(nullable = false)
    private String observation;

    private boolean enabled;
    
}
