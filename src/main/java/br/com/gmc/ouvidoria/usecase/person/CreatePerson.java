package br.com.gmc.ouvidoria.usecase.person;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.PersonGateway;
import br.com.gmc.ouvidoria.entity.model.Person;
import br.com.gmc.ouvidoria.entity.model.Role;
import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import br.com.gmc.ouvidoria.usecase.address.CreateAddress;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see PersonGateway
 * @see CreateAddress
 * @see RegisterDTO
 */
@Service
public class CreatePerson {

    private final PersonGateway gateway;
    private final CreateAddress createAddress;

    public CreatePerson(PersonGateway gateway, CreateAddress createAddress) {
        this.gateway = gateway;
        this.createAddress = createAddress;
    }

    public Person execute(RegisterDTO registerDTO) throws IllegalArgumentException {

        Person newPerson = new Person(registerDTO);
        if(!registerDTO.address().hideAddress()) {
            newPerson.setAddress(this.createAddress.execute(newPerson.getAddress()));
        } else {
            newPerson.setAddress(null);
        }
        newPerson.getUser().addRole(new Role("ROLE_COMUM"));
        return this.gateway.saveOrUpdate(newPerson);
    }

}
