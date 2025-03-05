package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import br.com.gmc.ouvidoria.infrastructure.dto.DefaultResponseDTO;
import br.com.gmc.ouvidoria.infrastructure.dto.RequestPasswordLinkDTO;
import br.com.gmc.ouvidoria.usecase.authentication.PasswordRecoveryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see PasswordRecoveryRequest
 * @see RequestPasswordLinkDTO
 * @see DefaultResponseDTO
 */
@RestController
@RequestMapping("/api/recovery")
public class PasswordRecoveryRestController {

    private final PasswordRecoveryRequest passwordRecoveryRequest;

    public PasswordRecoveryRestController(PasswordRecoveryRequest passwordRecoveryRequest) {
        this.passwordRecoveryRequest = passwordRecoveryRequest;
    }

    /**
     * Lida com requisição de envio de link de redefinição de senha
     * @param dto RequestPasswordLinkDTO
     * @return status 200 ou 400
     */
    @PostMapping("/request-link")
    public ResponseEntity<?> requestLink(@RequestBody RequestPasswordLinkDTO dto){

        try {
            this.passwordRecoveryRequest.execute(dto.email());
            return ResponseEntity.ok(new DefaultResponseDTO("Um link de redefinição de senha foi enviado para o seu e-mail"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new DefaultResponseDTO(e.getMessage()));
        }

    }

}
