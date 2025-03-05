package br.com.gmc.ouvidoria.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.Person;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Person
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

    Person findByUser_Username(String username);

}
