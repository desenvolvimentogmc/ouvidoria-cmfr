package br.com.gmc.ouvidoria.usecase.legalentity;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.LegalEntityGateway;
import br.com.gmc.ouvidoria.entity.model.LegalEntity;
import br.com.gmc.ouvidoria.entity.model.Role;
import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import br.com.gmc.ouvidoria.usecase.address.CreateAddress;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see LegalEntityGateway
 * @see CreateAddress
 * @see RegisterDTO
 */
@Service
public class CreateLegalEntity {

    private final LegalEntityGateway gateway;
    private final CreateAddress createAddress;

    public CreateLegalEntity(LegalEntityGateway gateway, CreateAddress createAddress) {
        this.gateway = gateway;
        this.createAddress = createAddress;
    }

    public LegalEntity execute(RegisterDTO dto) throws BadRequestException {
        LegalEntity legalEntity = new LegalEntity(dto);
        if(!dto.address().hideAddress()) {
            legalEntity.setAddress(this.createAddress.execute(legalEntity.getAddress()));
        } else {
            legalEntity.setAddress(null);
        }
        legalEntity.getUser().addRole(new Role("ROLE_COMUM"));
        return this.gateway.saveOrUpdate(legalEntity);
    }
    
}
