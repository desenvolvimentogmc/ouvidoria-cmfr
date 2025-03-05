package br.com.gmc.ouvidoria.entity.model;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;

/**
 * Classe que representa um usuário abstrato
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Role
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "tb_users")
public class User implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    
	@Id
    private String username;
    private String password;
    private boolean enabled;
    private boolean mustUpdatePassword;

    @Transient
    private Employee employee;

    @Transient
    private String confirmPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void encryptPassword() {
        this.password =  new BCryptPasswordEncoder().encode(this.password);
    }

    public User(RegisterDTO dto) throws IllegalArgumentException  {
        this.username = dto.email();
        
        if(dto.password().equals(dto.confirmPassword())) {
            this.password = new BCryptPasswordEncoder().encode(dto.password());
        }
        else {
            throw new IllegalArgumentException("As senhas não conferem.");
        }

        this.enabled = true;
    }
}
