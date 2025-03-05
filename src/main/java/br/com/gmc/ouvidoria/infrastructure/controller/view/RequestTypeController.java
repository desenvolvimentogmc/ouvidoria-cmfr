package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.entity.model.RequestType;
import br.com.gmc.ouvidoria.usecase.department.ListDepartments;
import br.com.gmc.ouvidoria.usecase.requesttype.CreateRequestType;
import br.com.gmc.ouvidoria.usecase.requesttype.FindRequestTypeById;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.com.gmc.ouvidoria.usecase.requesttype.ListRequestTypes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ListRequestTypes
 * @see CreateRequestType
 * @see ListDepartments
 */
@Controller
@RequestMapping({"/request-types", "/tipos-de-solicitacao"})
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ROOT')")
public class RequestTypeController {

    private static final String DEFAULT_CONTEXT = "pages/request-type";

    private final ListRequestTypes listRequestTypes;
    private final CreateRequestType createRequestType;
    private final ListDepartments listDepartments;
    private final FindRequestTypeById findRequestTypeById;

    public RequestTypeController(
            ListRequestTypes listRequestTypes,
            CreateRequestType createRequestType,
            ListDepartments listDepartments, FindRequestTypeById findRequestTypeById) {
        this.listRequestTypes = listRequestTypes;
        this.createRequestType = createRequestType;
        this.listDepartments = listDepartments;
        this.findRequestTypeById = findRequestTypeById;
    }

    /**
     * @param model interface
     * @return tela inicial dos tipos de requisição
     */
    @GetMapping
    public String requestType(Model model) {
        model.addAttribute("requestTypeList", this.listRequestTypes.execute());
        return DEFAULT_CONTEXT + "/index";
    }

    /**
     * @param requestType objeto a ser populado
     * @param model interface
     * @return formulário de cadastro de novo tipo de requisição
     */
    @GetMapping({"/new", "/novo-tipo-de-solicitacao"})
    public String newRequestType(RequestType requestType, Model model) {
        model.addAttribute("departments",  this.listDepartments.execute());
        return DEFAULT_CONTEXT + "/new";
    }

    /**
     * salva um novo tipo de requisição
     * @param requestType objeto preenchido
     * @param redirectAttributes interface
     * @param model interface
     * @return redireciona para tela de tipos de solicitação
     */
    @PostMapping({"/new", "/novo-tipo-de-solicitacao"})
    public String requestType(
            @Valid RequestType requestType,
            RedirectAttributes redirectAttributes,
            Model model) {
        try {
            this.createRequestType.execute(requestType);
            redirectAttributes.addFlashAttribute("success", "Tipo de requisição cadastrada com sucesso!");
            return "redirect:/tipos-de-solicitacao";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return newRequestType(requestType, model);
        }
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Integer id, Model model) {
        RequestType requestType = this.findRequestTypeById.execute(id);

        if(requestType == null) return "redirect:/404";

        model.addAttribute("requestType", requestType);
        model.addAttribute("departments", this.listDepartments.execute());
        return DEFAULT_CONTEXT + "/view";
    }
    
}
