package br.com.gmc.ouvidoria.usecase.employee;

import br.com.gmc.ouvidoria.entity.gateway.EmployeeGateway;
import br.com.gmc.ouvidoria.entity.model.Employee;
import br.com.gmc.ouvidoria.entity.model.User;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see EmployeeGateway
 */
@Service
public class FindEmployeeByUser {

    private final EmployeeGateway gateway;

    public FindEmployeeByUser(EmployeeGateway gateway) {
        this.gateway = gateway;
    }

    public Employee execute(User user) {
        return this.gateway.findByUser(user);
    }

    public Employee execute(String username) {
        return this.gateway.findByUser(username);
    }
}
