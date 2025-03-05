package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.usecase.users.FindUserByUsername;
import br.com.gmc.ouvidoria.usecase.users.UpdateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final FindUserByUsername findUserByUsername;
    private final UpdateUser updateUser;

    public UserRestController(FindUserByUsername findUserByUsername, UpdateUser updateUser) {
        this.findUserByUsername = findUserByUsername;
        this.updateUser = updateUser;
    }

    @PostMapping("/{username}/inactive")
    public ResponseEntity<?> inactive(@PathVariable String username) {
        try {
            User user = this.findUserByUsername.execute(username);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setEnabled(false);
            return ResponseEntity.ok(this.updateUser.execute(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{username}/active")
    public ResponseEntity<?> active(@PathVariable String username) {
        try {
            User user = this.findUserByUsername.execute(username);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            user.setEnabled(true);
            return ResponseEntity.ok(this.updateUser.execute(user));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
