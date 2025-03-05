package br.com.gmc.ouvidoria.usecase.authentication;

import br.com.gmc.ouvidoria.usecase.employee.FindEmployeeByUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.UserGateway;
import br.com.gmc.ouvidoria.entity.model.User;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see UserGateway
 * @see FindEmployeeByUser
 */
@Service
public class Authenticate implements UserDetailsService {

    private final UserGateway gateway;
    private final FindEmployeeByUser findEmployeeByUser;

    public Authenticate(UserGateway gateway, FindEmployeeByUser findEmployeeByUser) {
        this.gateway = gateway;
        this.findEmployeeByUser = findEmployeeByUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.gateway.findById(username);

        if(user == null) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos.");
        }

        user.setEmployee(this.findEmployeeByUser.execute(user));
        return user;
    }

}
