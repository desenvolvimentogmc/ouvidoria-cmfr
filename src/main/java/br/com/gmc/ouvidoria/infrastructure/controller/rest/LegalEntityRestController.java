package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmc.ouvidoria.entity.model.LegalEntity;
import br.com.gmc.ouvidoria.infrastructure.dto.RegisterDTO;
import br.com.gmc.ouvidoria.usecase.legalentity.CreateLegalEntity;
import jakarta.validation.Valid;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RegisterDTO
 * @see CreateLegalEntity
 */
@RestController
@RequestMapping("/api/legal-entities")
public class LegalEntityRestController {

    private final CreateLegalEntity createLegalEntity;

    public LegalEntityRestController(CreateLegalEntity createLegalEntity) {
        this.createLegalEntity = createLegalEntity;
    }

    /**
     * Salva uma pessoa jurídica
     * @param registerDTO objeto com as informações de LegalEntity
     * @return 204
     */
    @PostMapping
    public ResponseEntity<?> persistLegalEntity(@RequestBody @Valid RegisterDTO registerDTO) throws BadRequestException {
        LegalEntity newLegalEntity = this.createLegalEntity.execute(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLegalEntity);
    }
    
    
}
