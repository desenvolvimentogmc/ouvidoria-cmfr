package br.com.gmc.ouvidoria.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.LegalEntity;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see LegalEntity
 */
@Repository
public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {

    LegalEntity findByUser_Username(String username);
    
}
