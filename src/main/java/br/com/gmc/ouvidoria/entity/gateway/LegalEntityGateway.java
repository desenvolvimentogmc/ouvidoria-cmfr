package br.com.gmc.ouvidoria.entity.gateway;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.LegalEntity;
import br.com.gmc.ouvidoria.infrastructure.repository.LegalEntityRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see LegalEntityRepository
 * @see LegalEntity
 */
@Component
public class LegalEntityGateway {

    private final LegalEntityRepository repository;

    public LegalEntityGateway(LegalEntityRepository repository) {
        this.repository = repository;
    }

    public LegalEntity saveOrUpdate(LegalEntity newEntity) {
        return this.repository.save(newEntity);
    }

    public LegalEntity findByUser(String username) {
        return this.repository.findByUser_Username(username);
    }
    
}
