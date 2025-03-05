package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import br.com.gmc.ouvidoria.entity.model.Department;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.infrastructure.dto.StatusDTO;
import br.com.gmc.ouvidoria.usecase.department.FindDepartmentById;
import br.com.gmc.ouvidoria.usecase.request.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/requests")
public class RequestRestController {

    private final ListRequestsByStatusAndDepartment listRequestsByStatusAndDepartment;
    private final ListRequests listRequests;
    private final FindDepartmentById findDepartmentById;
    private final UpdateRequest updateRequest;
    private final FindRequestById findRequestById;
    private final ListRequestsByStatus listRequestsByStatus;

    public RequestRestController(
            ListRequestsByStatusAndDepartment listRequestsByStatusAndDepartment,
            ListRequests listRequests,
            FindDepartmentById findDepartmentById,
            UpdateRequest updateRequest,
            FindRequestById findRequestById, ListRequestsByStatus listRequestsByStatus) {
        this.listRequestsByStatusAndDepartment = listRequestsByStatusAndDepartment;
        this.listRequests = listRequests;
        this.findDepartmentById = findDepartmentById;
        this.updateRequest = updateRequest;
        this.findRequestById = findRequestById;
        this.listRequestsByStatus = listRequestsByStatus;
    }

    /**
     * @param status da solicitação
     * @param department do usuário ou departamento da solicitação
     * @return Todos as solicitação ou Todas as solicitação por status e departamento
     */
    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Long requestId,
            HttpServletRequest httpServletRequest) {
        if(status == null && department == null && requestId == null) {
            return ResponseEntity.ok(this.listRequests.execute());
        }

        if(requestId != null) {
            Request request = this.findRequestById.execute(requestId);
            //verifica se o usuário é dono da requisição
            if(request == null ||
                (httpServletRequest.isUserInRole("ROLE_COMUM") &&  !request.isRequesterOwner(httpServletRequest.getRemoteUser()))) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(request);
        }

        if(Objects.equals(department, "all")) {
            return ResponseEntity.ok(this.listRequestsByStatus.execute(Status.valueOf(status)));
        }

        Department dept = this.findDepartmentById.execute(Integer.valueOf(department));

        if(dept == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this.listRequestsByStatusAndDepartment.execute(Status.valueOf(status), dept));
    }

    /**
     * Atualiza status da requisição
     * @param id da requisição
     * @param status novo valor para o campo
     * @return solicitação atualizada
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody StatusDTO status) {
        Request request = this.findRequestById.execute(id);

        if(request == null) {
            return ResponseEntity.badRequest().build();
        }

        request.setStatus(Status.valueOf(status.status()));
        request = this.updateRequest.execute(request);

        return ResponseEntity.ok(request);
    }
}
