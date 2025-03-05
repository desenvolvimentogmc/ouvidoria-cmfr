package br.com.gmc.ouvidoria.usecase.authentication;

import br.com.gmc.ouvidoria.entity.gateway.RoleGateway;
import br.com.gmc.ouvidoria.entity.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListRoles {

    private final RoleGateway gateway;

    public ListRoles(RoleGateway gateway) {
        this.gateway = gateway;
    }

    public List<Role> execute() {
        return this.gateway.findAll();
    }
}
