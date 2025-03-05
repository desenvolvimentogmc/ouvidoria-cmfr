package br.com.gmc.ouvidoria.entity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa os tipos de requisições que os departamentos respondem
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Department
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_request_types")
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Integer timeLimit; //dias para responder

    @ManyToOne(optional = false)
    private Department department;

    private boolean active;

}
