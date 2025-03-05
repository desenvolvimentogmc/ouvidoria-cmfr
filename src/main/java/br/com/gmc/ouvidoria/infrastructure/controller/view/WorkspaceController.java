package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.entity.model.User;
import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByDepartmentAndStatus;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping({"/workspace", "/area-de-trabalho"})
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
public class WorkspaceController {

    private static final String DEFAULT_CONTEXT = "pages/workspace";

    private final CountRequestsByStatus countRequestsByStatus;
    private final CountRequestsByDepartmentAndStatus countRequestsByDepartmentAndStatus;

    public WorkspaceController(CountRequestsByStatus countRequestsByStatus,
                               CountRequestsByDepartmentAndStatus countRequestsByDepartmentAndStatus) {
        this.countRequestsByStatus = countRequestsByStatus;
        this.countRequestsByDepartmentAndStatus = countRequestsByDepartmentAndStatus;
    }

    /**
     * @param model
     * @return tela de visualização de demandas
     */
    @GetMapping
    public String workspace(Model model, HttpServletRequest request) {

        User authenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if(request.isUserInRole("ROLE_ROOT")) {
            model.addAttribute("department", "all");
            model.addAttribute("requests_" + Status.OPEN, this.countRequestsByStatus.execute(Status.OPEN));
            model.addAttribute("requests_" + Status.UNDER_ANALYSIS, this.countRequestsByStatus.execute(Status.UNDER_ANALYSIS));
            model.addAttribute("requests_" + Status.ANSWERED, this.countRequestsByStatus.execute(Status.ANSWERED));
            model.addAttribute("requests_" + Status.APPEAL, this.countRequestsByStatus.execute(Status.APPEAL));
            model.addAttribute("requests_" + Status.CLOSED, this.countRequestsByStatus.execute(Status.CLOSED));
        }
        else {
            System.out.println(authenticated.toString());
            model.addAttribute("department", authenticated.getEmployee().getDepartment().getId());
            model.addAttribute("requests_" + Status.OPEN, this.countRequestsByDepartmentAndStatus.execute(authenticated.getEmployee().getDepartment().getId(), Status.OPEN));
            model.addAttribute("requests_" + Status.UNDER_ANALYSIS, this.countRequestsByDepartmentAndStatus.execute(authenticated.getEmployee().getDepartment().getId(), Status.UNDER_ANALYSIS));
            model.addAttribute("requests_" + Status.ANSWERED, this.countRequestsByDepartmentAndStatus.execute(authenticated.getEmployee().getDepartment().getId(), Status.ANSWERED));
            model.addAttribute("requests_" + Status.APPEAL, this.countRequestsByDepartmentAndStatus.execute(authenticated.getEmployee().getDepartment().getId(), Status.APPEAL));
            model.addAttribute("requests_" + Status.CLOSED, this.countRequestsByDepartmentAndStatus.execute(authenticated.getEmployee().getDepartment().getId(), Status.CLOSED));
        }


        return DEFAULT_CONTEXT + "/index";
    }
}
