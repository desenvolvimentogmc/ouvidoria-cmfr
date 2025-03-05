package br.com.gmc.ouvidoria.usecase.users;

import br.com.gmc.ouvidoria.entity.gateway.UserGateway;
import br.com.gmc.ouvidoria.entity.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateUser {

    private final UserGateway gateway;

    public UpdateUser(UserGateway gateway) {
        this.gateway = gateway;
    }

    public User execute(String password, String confirmPassword, User user){
        if(user == null || !user.isMustUpdatePassword()) {
            throw new UsernameNotFoundException("Usuário não existe.");
        }

        if(!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("As senhas não correspondem");
        }

        user.setPassword(password);
        user.encryptPassword();
        user.setMustUpdatePassword(false);
        return this.gateway.saveOrUpdate(user);
    }

    public User execute(User user){
        return this.gateway.saveOrUpdate(user);
    }
}
