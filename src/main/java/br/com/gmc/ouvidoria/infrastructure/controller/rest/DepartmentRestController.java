package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import br.com.gmc.ouvidoria.usecase.department.ToggleDepartmentEnabled;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ToggleDepartmentEnabled
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private final ToggleDepartmentEnabled toggleDepartmentEnabled;

    public DepartmentRestController(ToggleDepartmentEnabled toggleDepartmentEnabled) {
        this.toggleDepartmentEnabled = toggleDepartmentEnabled;
    }

    /**
     * método desabilita ou habilita um departamento
     * @param id Integer
     * @return Status 200 ou 404
     */
    @PatchMapping("/{id}/toggle-enable")
    public ResponseEntity<?> toggleEnableDepartment(@PathVariable Integer id) {
        try {
            this.toggleDepartmentEnabled.execute(id);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

