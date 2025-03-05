package br.com.gmc.ouvidoria.usecase.address;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.AddressGateway;
import br.com.gmc.ouvidoria.entity.model.Address;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see AddressGateway
 * @see FindAddressById
 * @see Address
 */
@Service
public class CreateAddress {
 
    private final AddressGateway gateway;
    private final FindAddressById findAddressById;

    public CreateAddress(AddressGateway gateway, FindAddressById findAddressById) {
        this.gateway = gateway;
        this.findAddressById = findAddressById;
    }

    public Address execute(Address newAddress) {
        Address address = this.findAddressById.execute(newAddress.getCep());
        return address == null ? this.gateway.saveOrUpdate(newAddress) : address;
    }

}
