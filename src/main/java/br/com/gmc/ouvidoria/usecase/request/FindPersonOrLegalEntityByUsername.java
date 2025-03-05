package br.com.gmc.ouvidoria.usecase.request;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.model.LegalEntity;
import br.com.gmc.ouvidoria.entity.model.Person;
import br.com.gmc.ouvidoria.usecase.legalentity.FindLegalEntityByUser;
import br.com.gmc.ouvidoria.usecase.person.FindPersonByUser;
import jakarta.persistence.EntityNotFoundException;

@Service
public class FindPersonOrLegalEntityByUsername {

    private final FindPersonByUser findPersonByUser;
    private final FindLegalEntityByUser findLegalEntityByUser;

    public FindPersonOrLegalEntityByUsername(FindPersonByUser findPersonByUser, FindLegalEntityByUser findLegalEntityByUser) {
        this.findPersonByUser = findPersonByUser;
        this.findLegalEntityByUser = findLegalEntityByUser;
    }


    public Object execute(String username) {
        Person person = this.findPersonByUser.execute(username);

        if(person != null) return person;

        LegalEntity legalEntity = this.findLegalEntityByUser.execute(username);

        if(legalEntity != null) return legalEntity;

        throw new EntityNotFoundException("Não foi encontrado nenhuma Pessoa Física nem Jurídica com esse usuário");
    }

}
