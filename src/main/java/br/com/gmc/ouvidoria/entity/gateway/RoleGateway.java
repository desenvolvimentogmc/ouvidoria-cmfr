package br.com.gmc.ouvidoria.entity.gateway;

import br.com.gmc.ouvidoria.entity.model.Role;
import br.com.gmc.ouvidoria.infrastructure.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleGateway {

    private final RoleRepository repository;

    public RoleGateway(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAll() {
        return this.repository.findAll();
    }
}
