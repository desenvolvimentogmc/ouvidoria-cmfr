package br.com.gmc.ouvidoria.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.Role;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Role
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
