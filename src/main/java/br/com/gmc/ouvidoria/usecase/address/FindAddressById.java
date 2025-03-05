package br.com.gmc.ouvidoria.usecase.address;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.AddressGateway;
import br.com.gmc.ouvidoria.entity.model.Address;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see AddressGateway
 */
@Service
public class FindAddressById {
    
    private final AddressGateway gateway;

    public FindAddressById(AddressGateway gateway) {
        this.gateway = gateway;
    }

    public Address execute(String cep) {
        return this.gateway.findById(cep);
    }

}
