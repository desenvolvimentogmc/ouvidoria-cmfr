package br.com.gmc.ouvidoria.usecase.authentication;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.usecase.users.FindUserByUsername;
import br.com.gmc.ouvidoria.usecase.users.UpdateUser;
import br.com.gmc.ouvidoria.utils.JwtUtil;
import br.com.gmc.ouvidoria.utils.SendMimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryRequest {

    @Value("${spring.application.url}")
    private String url;

    private final FindUserByUsername findUserByUsername;
    private final SendMimeMessage sendMimeMessage;
    private final JwtUtil jwtUtil;
    private final UpdateUser updateUser;

    public PasswordRecoveryRequest(FindUserByUsername findUserByUsername,
                                   SendMimeMessage sendMimeMessage, JwtUtil jwtUtil, UpdateUser updateUser){
        this.findUserByUsername = findUserByUsername;
        this.sendMimeMessage = sendMimeMessage;
        this.jwtUtil = jwtUtil;
        this.updateUser = updateUser;
    }

    public void execute(String email){

        User user = this.findUserByUsername.execute(email);

        if(user == null){
            throw new IllegalArgumentException("Argumentos inválidos");
        }

        String token = this.jwtUtil.generateResetToken(user.getUsername());

        var link = url + "/changepwd?token=" + token;

        this.sendMimeMessage.sendResetPasswordLink(
                user.getUsername(),
                "Redefinição de senha",
                link,
                "reset"
        );

        user.setMustUpdatePassword(true);
        this.updateUser.execute(user);

    }
}
