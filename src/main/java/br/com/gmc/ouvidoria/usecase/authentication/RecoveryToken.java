package br.com.gmc.ouvidoria.usecase.authentication;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.infrastructure.dto.TokenRecoveredDTO;
import br.com.gmc.ouvidoria.usecase.users.FindUserByUsername;
import br.com.gmc.ouvidoria.utils.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class RecoveryToken {

    private final JwtUtil jwtUtil;
    private final FindUserByUsername findUserByUsername;

    public RecoveryToken(JwtUtil jwtUtil, FindUserByUsername findUserByUsername) {
        this.jwtUtil = jwtUtil;
        this.findUserByUsername = findUserByUsername;
    }

    public TokenRecoveredDTO execute(String token) {

        if(!this.jwtUtil.isTokenValid(token)) {
            throw new IllegalArgumentException("Sessão expirada.");
        }

        String email = this.jwtUtil.extractEmail(token);

        User user = this.findUserByUsername.execute(email);

        if(user == null){
            throw new EntityNotFoundException("Tentativa de recuperação de senha incompleta: usuário não encontrado");
        }

        if(!user.isMustUpdatePassword()) {
            throw new EntityNotFoundException("Tentativa de recuperação de senha incompleta: link inválido.");
        }

        return new TokenRecoveredDTO(user, token);

    }
}
