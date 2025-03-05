package br.com.gmc.ouvidoria.entity.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;

/**
 * Classe que representa uma regra de acesso
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_roles")
public class Role implements GrantedAuthority {

    @Serial
    private static final long serialVersionUID = 1L;

	@Id
    private String name;

    private String description;

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

}
