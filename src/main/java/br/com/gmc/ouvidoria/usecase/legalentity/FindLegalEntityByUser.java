package br.com.gmc.ouvidoria.usecase.legalentity;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.LegalEntityGateway;
import br.com.gmc.ouvidoria.entity.model.LegalEntity;

@Service
public class FindLegalEntityByUser {
    
    private final LegalEntityGateway gateway;

    public FindLegalEntityByUser(LegalEntityGateway gateway) {
        this.gateway = gateway;
    }

    public LegalEntity execute(String username) {
        return this.gateway.findByUser(username);
    }

}
