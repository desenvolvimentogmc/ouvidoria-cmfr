package br.com.gmc.ouvidoria.entity.gateway;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Address;
import br.com.gmc.ouvidoria.infrastructure.repository.AddressRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Address
 * @see AddressRepository
 */
@Component
public class AddressGateway {
    
    private final AddressRepository repository;

    public AddressGateway(AddressRepository repository) {
        this.repository = repository;
    }

    public Address saveOrUpdate(Address newAddress) {
        return this.repository.save(newAddress);
    }

    public Address findById(String cep) {
        return this.repository.findById(cep).orElse(null);
    }

}
