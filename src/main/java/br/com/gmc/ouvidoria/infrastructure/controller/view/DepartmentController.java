package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByDepartmentAndStatus;
import br.com.gmc.ouvidoria.usecase.request.ListRequestsByDepartment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gmc.ouvidoria.entity.model.Department;
import br.com.gmc.ouvidoria.usecase.department.CreateDepartment;
import br.com.gmc.ouvidoria.usecase.department.FindDepartmentById;
import br.com.gmc.ouvidoria.usecase.department.ListDepartments;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see CreateDepartment
 * @see ListDepartments
 * @see FindDepartmentById
 * @see Department
 */
@Controller
@RequestMapping({"/departments", "/departamentos"})
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
public class DepartmentController {

    private static final String DEFAULT_CONTEXT = "pages/department";

    private final CreateDepartment createDepartment;
    private final ListDepartments listDepartments;
    private final FindDepartmentById findDepartmentById;
    private final CountRequestsByDepartmentAndStatus countRequestsByDepartmentAndStatus;
    private final ListRequestsByDepartment listRequestsByDepartment;

    public DepartmentController(CreateDepartment createDepartment,
                                ListDepartments listDepartments,
                                FindDepartmentById findDepartmentById,
                                CountRequestsByDepartmentAndStatus countRequestsByDepartmentAndStatus,
                                ListRequestsByDepartment listRequestsByDepartment) {
        this.createDepartment = createDepartment;
        this.listDepartments = listDepartments;
        this.findDepartmentById = findDepartmentById;
        this.countRequestsByDepartmentAndStatus = countRequestsByDepartmentAndStatus;
        this.listRequestsByDepartment = listRequestsByDepartment;
    }

    /**
     * @param model interface
     * @return view inicial
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("departments", this.listDepartments.execute());
        return DEFAULT_CONTEXT + "/index";
    }

    /**
     * @param id do departamento
     * @param model interface
     * @return tela de visualização do departamento
     */
    @GetMapping("/{id}")
    public String getDepartment(@PathVariable Integer id, Model model) {
        model.addAttribute("department", this.findDepartmentById.execute(id));
        model.addAttribute("numberOfOpenRequests", this.countRequestsByDepartmentAndStatus.execute(id, Status.OPEN));
        model.addAttribute("numberOfClosedRequests", this.countRequestsByDepartmentAndStatus.execute(id, Status.CLOSED));
        model.addAttribute("requests", this.listRequestsByDepartment.execute(id));
        return DEFAULT_CONTEXT + "/view";
    }
    
    /**
     * @param department objeto que será utilizado no formulário
     * @return formulário de cadastro de departamento
     */
    @GetMapping({"/new", "/novo-departamento"})
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String newDepartment(Department department) {
        return DEFAULT_CONTEXT + "/new";
    }

    /**
     * Salva um departamento
     * @param department objeto preenchido via formulário
     * @param ra interface
     * @return redireciona para tela de departamentos
     */
    @PostMapping({"/new", "/novo-departamento"})
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String newDepartment(@Valid Department department, RedirectAttributes ra) {

        try {
            Department newDepartament = this.createDepartment.execute(department);
            ra.addFlashAttribute("success", "Departamento " + newDepartament.getName() + " salvo com sucesso!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Não foi possível salvar o departamento.");
        }
        
        return "redirect:/departamentos";
    }
    
}
