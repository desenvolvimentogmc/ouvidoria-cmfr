package br.com.gmc.ouvidoria.usecase.person;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.PersonGateway;
import br.com.gmc.ouvidoria.entity.model.Person;

@Service
public class FindPersonByUser {

    private final PersonGateway gateway;

    public FindPersonByUser(PersonGateway gateway) {
        this.gateway = gateway;
    }

    public Person execute(String username) {
        return this.gateway.findByUsername(username);
    }
    
}
