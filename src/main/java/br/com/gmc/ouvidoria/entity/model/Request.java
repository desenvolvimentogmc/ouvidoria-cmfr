package br.com.gmc.ouvidoria.entity.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.gmc.ouvidoria.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe que representa uma Requisição/Solicitação
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestType
 * @see User
 * @see Department
 * @see File
 * @see Status
 * @see Response
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @ManyToOne(optional = false)
    private RequestType requestType;

    @ManyToOne(optional = false)
    private User requester;

    private LocalDateTime createdAt;

    private  LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    private List<File> attachments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "request")
    private List<Response> responses = new ArrayList<>();

    public void addAttachment(File newAttachment) {
        this.attachments.add(newAttachment);
    }

    public boolean isRequesterOwner(String requesterUsername) {
        return this.requester.getUsername().equals(requesterUsername);
    }
}
