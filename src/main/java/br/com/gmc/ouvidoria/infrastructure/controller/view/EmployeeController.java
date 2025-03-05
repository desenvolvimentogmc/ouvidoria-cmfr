package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.entity.model.Employee;
import br.com.gmc.ouvidoria.usecase.authentication.ListRoles;
import br.com.gmc.ouvidoria.usecase.employee.UpdateEmployee;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.gmc.ouvidoria.usecase.department.ListDepartments;
import br.com.gmc.ouvidoria.usecase.employee.CreateEmployee;
import br.com.gmc.ouvidoria.usecase.employee.FindEmployeeById;
import br.com.gmc.ouvidoria.usecase.employee.ListEmployees;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ListEmployees
 * @see CreateEmployee
 * @see FindEmployeeById
 * @see ListDepartments
 * @see UpdateEmployee
 * @see ListRoles
 * @see Employee
 */
@Controller
@RequestMapping({ "/employees", "/funcionarios" })
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
public class EmployeeController {

    private static final String DEFAULT_CONTEXT = "pages/employees";

    private final ListEmployees listEmployees;
    private final CreateEmployee createEmployee;
    private final FindEmployeeById findEmployeeById;
    private final ListDepartments listDepartments;
    private final UpdateEmployee updateEmployee;
    private final ListRoles listRoles;

    public EmployeeController(ListEmployees listEmployees,
                              CreateEmployee createEmployee,
                              FindEmployeeById findEmployeeById,
                              ListDepartments listDepartments,
                              UpdateEmployee updateEmployee,
                              ListRoles listRoles) {
        this.listEmployees = listEmployees;
        this.createEmployee = createEmployee;
        this.findEmployeeById = findEmployeeById;
        this.listDepartments = listDepartments;
        this.updateEmployee = updateEmployee;
        this.listRoles = listRoles;
    }

    /**
     * @param model interface
     * @return view inicial de funcionários
     */
    @GetMapping
    public String employees(Model model) {
        model.addAttribute("employeeList", this.listEmployees.execute());
        return DEFAULT_CONTEXT + "/index";
    }

    /**
     * 
     * @param model interface
     * @param employee objeto a ser persistido
     * @return formulário de cadastro de funcionário
     */
    @GetMapping({"/new", "/novo-acesso"})
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String newEmployee(Model model, Employee employee) {
        model.addAttribute("departments", this.listDepartments.execute());
        model.addAttribute("roles", this.listRoles.execute());
        return DEFAULT_CONTEXT + "/new";
    }

    /**
     * 
     * @param employee objeto preenchido do formulário
     * @param model interface
     * @return redireciona para tela de funcionários
     */
    @PostMapping({"/new", "/novo-acesso"})
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String newEmployee(Employee employee, @RequestParam String role,  Model model, RedirectAttributes redirectAttributes) {
        try {
            this.createEmployee.execute(employee, role);
            redirectAttributes.addFlashAttribute("success", "Funcionário criado com sucesso");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }

        return "redirect:/funcionarios";
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String update(Employee employee, Model model, RedirectAttributes redirectAttributes) {
        try {
            Employee oldEmployee = this.findEmployeeById.execute(employee.getId());
            this.updateEmployee.execute(oldEmployee, employee);
            redirectAttributes.addFlashAttribute("success", "Funcionário atualizado com sucesso!");
        } catch (NullPointerException ex) {
            redirectAttributes.addFlashAttribute("error", "Funcionário inválido ou não localizado.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Funcionário inválido.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao realizar operação.");
        }
        return "redirect:/funcionarios/" + employee.getId();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ROOT')")
    public String employee(@PathVariable Integer id, Model model) {
        model.addAttribute("employee", this.findEmployeeById.execute(id));
        model.addAttribute("departments", this.listDepartments.execute());
        return DEFAULT_CONTEXT + "/view";
    }


}
