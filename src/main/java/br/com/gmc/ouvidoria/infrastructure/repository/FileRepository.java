package br.com.gmc.ouvidoria.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.File;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see File
 */
@Repository
public interface FileRepository extends JpaRepository<File, UUID>{

}
