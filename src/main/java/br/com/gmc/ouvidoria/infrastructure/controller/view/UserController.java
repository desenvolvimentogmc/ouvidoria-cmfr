package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.usecase.authentication.ListRoles;
import br.com.gmc.ouvidoria.usecase.users.FindUserByUsername;
import br.com.gmc.ouvidoria.usecase.users.UpdateUserPassword;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/users", "/usuarios"})
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
public class UserController {

    private static final String DEFAULT_CONTEXT = "pages/users";

    private final UpdateUserPassword updateUserPassword;
    private final FindUserByUsername findUserByUsername;
    private final ListRoles listRoles;

    public UserController(UpdateUserPassword updateUserPassword, FindUserByUsername findUserByUsername, ListRoles listRoles) {
        this.updateUserPassword = updateUserPassword;
        this.findUserByUsername = findUserByUsername;
        this.listRoles = listRoles;
    }

    @GetMapping("/{username}")
    public String view(@PathVariable("username") String username, Model model){
        User user = this.findUserByUsername.execute(username);

        if(user == null){
            return "redirect:/404";
        }

        model.addAttribute("user", user);
        model.addAttribute("roles", this.listRoles.execute());
        return DEFAULT_CONTEXT + "/view";
    }

    @GetMapping("/changepwd")
    public String changepwd(HttpServletRequest request, Model model) {

        return DEFAULT_CONTEXT + "/changepwd";
    }

    @PostMapping("/changepwd")
    public String changepwd(HttpServletRequest request, @RequestParam String password, @RequestParam String confirmPassword, RedirectAttributes ra) {

        try {
            User user = this.findUserByUsername.execute(request.getRemoteUser());
            this.updateUserPassword.execute(password, confirmPassword, user);
            return "redirect:/inicio";
        } catch (UsernameNotFoundException e) {
            ra.addFlashAttribute("error", "Erro ao realizar operação");
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/logout";
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "As senhas não são iguais.");
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users/changepwd?error";
        }

    }
}
