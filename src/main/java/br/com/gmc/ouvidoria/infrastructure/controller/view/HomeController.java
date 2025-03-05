package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.request.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ListRequests
 */
@Controller
@RequestMapping({"/home", "/inicio"})
public class HomeController {

    private static final String DEFAULT_CONTEXT = "pages/home";

    private final ListRequestsByRequesterAndStatusNot listRequestsByRequesterAndStatusNot;
    private final ListRequestsByStatusNot listRequestsByStatusNot;

    public HomeController(
            ListRequestsByRequesterAndStatusNot listRequestsByRequesterAndStatusNot,
            ListRequestsByStatusNot listRequestsByStatusNot) {
        this.listRequestsByRequesterAndStatusNot = listRequestsByRequesterAndStatusNot;
        this.listRequestsByStatusNot = listRequestsByStatusNot;
    }

    /**   por tipo de usuário
     * @param model interface
     * @return página inicial
     */
    @GetMapping
    public String index(Model model, HttpServletRequest request) {

        //caso seja usuário comum, carrega as solicitações não fechadas
        if(request.isUserInRole("ROLE_COMUM")) {
            model.addAttribute("requests", this.listRequestsByRequesterAndStatusNot.execute(request.getRemoteUser(), Status.CLOSED));
            return DEFAULT_CONTEXT + "/index";
        }



        //caso seja admin, visualiza os chamados do departamento
        if(request.isUserInRole("ROLE_ADMIN")) {
            model.addAttribute("requests", this.listRequestsByRequesterAndStatusNot.execute(request.getRemoteUser(), Status.CLOSED));
        }
        //caso seja root, visualiza todos os chamados
        else {
            model.addAttribute("requests", this.listRequestsByStatusNot.execute(Status.CLOSED));
        }

        return "redirect:/area-de-trabalho";
    }
    
    
}
