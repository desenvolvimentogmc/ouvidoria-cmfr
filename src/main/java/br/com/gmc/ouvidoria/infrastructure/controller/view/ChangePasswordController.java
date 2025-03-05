package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.infrastructure.dto.TokenRecoveredDTO;
import br.com.gmc.ouvidoria.usecase.authentication.RecoveryToken;
import br.com.gmc.ouvidoria.usecase.users.UpdateUserPassword;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Lida com requisição de mudança de senha
 * Essa controladora recebe a requisição com token e direciona
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RecoveryToken
 * @see UpdateUserPassword
 */
@Controller
@RequestMapping("/changepwd")
public class ChangePasswordController {

    private final RecoveryToken recoveryToken;
    private final UpdateUserPassword updateUserPassword;

    public ChangePasswordController(RecoveryToken recoveryToken, UpdateUserPassword updateUserPassword) {
        this.recoveryToken = recoveryToken;
        this.updateUserPassword = updateUserPassword;
    }

    /**
     * @param token no formato de query param - é um token jwt
     * @param model interface
     * @return página de alteração de senha
     */
    @GetMapping
    public String changePassword(@RequestParam String token, Model model){
        try {
            TokenRecoveredDTO dto = this.recoveryToken.execute(token);
            model.addAttribute("username", dto.user().getUsername());
            model.addAttribute("token", dto.token());
            return "pages/auth/changepwd";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            return "redirect:/404";
        }
    }

    /**
     * @param token jwt
     * @param password String
     * @param confirmPassword String
     * @param ra interface
     * @return página inicial
     */
    @PostMapping
    public String changepwd(@RequestParam String token, @RequestParam String password, @RequestParam String confirmPassword, RedirectAttributes ra) {
        try {
            TokenRecoveredDTO dto = this.recoveryToken.execute(token);
            this.updateUserPassword.execute(password, confirmPassword, dto.user());
            return "redirect:/inicio";
        } catch (UsernameNotFoundException e) {
            ra.addFlashAttribute("error", "Erro ao realizar operação");
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/logout";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            ra.addFlashAttribute("error", "As senhas não são iguais.");
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users/changepwd?error";
        }
    }
}
