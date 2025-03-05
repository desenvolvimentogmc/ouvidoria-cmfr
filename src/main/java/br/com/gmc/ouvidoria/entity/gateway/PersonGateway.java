package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Person;
import br.com.gmc.ouvidoria.infrastructure.repository.PersonRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see PersonRepository
 * @see Person
 */
@Component
public class PersonGateway {
	
	private final PersonRepository repository;

	public PersonGateway(PersonRepository repository) {
		super();
		this.repository = repository;
	}
	
	/**
	 * 
	 * @param newPerson Person
	 * @return pessoa salva
	 */
	public Person saveOrUpdate(Person newPerson) {
		return this.repository.save(newPerson);
	}
	
	/**
	 * 
	 * @return lista de pessoas
	 */
	public List<Person> findAll() {
		return this.repository.findAll();
	}
	
	/**
	 * 
	 * @param personId Long
	 * @return pessoa encontrada
	 */
	public Person findById(Long personId) {
		return this.repository.findById(personId).orElse(null);
	}

    public Person findByUsername(String username) {
        return this.repository.findByUser_Username(username);
    }

}
