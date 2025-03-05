package br.com.gmc.ouvidoria.usecase.users;

import br.com.gmc.ouvidoria.entity.gateway.UserGateway;
import br.com.gmc.ouvidoria.entity.model.User;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see FindUserByUsername
 */
@Service
public class FindUserByUsername {

    private final UserGateway gateway;

    public FindUserByUsername(UserGateway gateway) {
        this.gateway = gateway;
    }

    public User execute(String username){
        return this.gateway.findById(username);
    }
}
