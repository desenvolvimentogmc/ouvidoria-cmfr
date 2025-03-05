package br.com.gmc.ouvidoria.entity.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Classe que representa um arquivo
 * Os arquivos são usados na abertura de requisição 
 * Os arquivos são usados nas respostas 
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_files")
@ToString
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String path;

    private String originalFileName;

    public File(String path, String originalFileName) {
        this.path = path;
        this.originalFileName = originalFileName;
    }
}
