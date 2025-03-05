package br.com.gmc.ouvidoria.entity.gateway;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.infrastructure.repository.UserRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see UserRepository
 */
@Component
public class UserGateway {

    private final UserRepository repository;

    public UserGateway (UserRepository repository) {
        this.repository = repository;
    }

    public User findById(String username) {
        return this.repository.findById(username).orElse(null);
    }

    public User saveOrUpdate(User user) {
        return this.repository.save(user);
    }
}
