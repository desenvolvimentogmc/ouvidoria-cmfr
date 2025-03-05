package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmc.ouvidoria.entity.model.Person;
import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import br.com.gmc.ouvidoria.usecase.person.CreatePerson;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RegisterDTO
 */
@RestController
@RequestMapping("/api/persons")
public class PersonRestController {

    private final CreatePerson createPerson;

    public PersonRestController(CreatePerson createPerson) {
        this.createPerson = createPerson;
    }

    @GetMapping
    public ResponseEntity<?> getPersons() {
        return ResponseEntity.ok().build();
    }

    /**
     * Salva uma pessoa física 
     * @param registerDTO dados de pessoa física
     * @return 204
     */
    @PostMapping
    public ResponseEntity<Person> persistPerson(@RequestBody @Valid RegisterDTO registerDTO) {
        Person newPerson = this.createPerson.execute(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPerson);
    }
    
}
