package br.com.gmc.ouvidoria.entity.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa uma resposta de um ténico à requisição
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see File
 * @see Request
 * @see User
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<File> attachments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private Request request;

    @ManyToOne
    private User user;

    public void addAttachment(File file) {
        this.attachments.add(file);
    }
}
