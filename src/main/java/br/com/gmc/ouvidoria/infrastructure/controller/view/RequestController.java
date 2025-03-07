package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.entity.model.Response;
import br.com.gmc.ouvidoria.enums.Category;
import br.com.gmc.ouvidoria.usecase.employee.FindEmployeeByUser;
import br.com.gmc.ouvidoria.usecase.request.*;
import br.com.gmc.ouvidoria.usecase.requesttype.ListRequestTypes;
import br.com.gmc.ouvidoria.usecase.users.FindUserByUsername;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ListRequestTypes
 * @see CreateRequest
 * @see FindRequestById
 * @see CreateResponse 
 */
@Controller
@RequestMapping({"/requests", "/solicitacoes"})
public class RequestController {

    private static final String DEFAULT_CONTEXT = "pages/requests";

    private final ListRequestTypes listRequestTypes;
    private final CreateRequest createRequest;
    private final FindRequestById findRequestById;
    private final CreateResponse createResponse;
    private final ListRequestsByRequester listRequestsByRequester;
    private final ListRequestsByDepartment listRequestsByDepartment;
    private final FindEmployeeByUser findEmployeeByUser;
    private final ListRequests listRequests;
    private final FindPersonOrLegalEntityByUsername findPersonOrLegalEntityByUsername;

    public RequestController(
            ListRequestTypes listRequestTypes,
            CreateRequest createRequest,
            FindUserByUsername findUserByUsername,
            FindRequestById findRequestById,
            CreateResponse createResponse,
            ListRequestsByRequester listRequestsByRequester,
            ListRequestsByDepartment listRequestsByDepartment,
            FindEmployeeByUser findEmployeeByUser, 
            ListRequests listRequests, 
            FindPersonOrLegalEntityByUsername findPersonOrLegalEntityByUsername) {
        this.listRequestTypes = listRequestTypes;
        this.createRequest = createRequest;
        this.findRequestById = findRequestById;
        this.createResponse = createResponse;
        this.listRequestsByRequester = listRequestsByRequester;
        this.listRequestsByDepartment = listRequestsByDepartment;
        this.findEmployeeByUser = findEmployeeByUser;
        this.listRequests = listRequests;
        this.findPersonOrLegalEntityByUsername = findPersonOrLegalEntityByUsername;
    }

    @GetMapping
    public String requests(HttpServletRequest request, Model model) {
        if(request.isUserInRole("ROLE_COMUM")){
            model.addAttribute("requests", this.listRequestsByRequester.execute(request.getRemoteUser()));
        }
        else if(request.isUserInRole("ROLE_ADMIN")){
            model.addAttribute("requests",
                    this.listRequestsByDepartment
                            .execute(this.findEmployeeByUser.execute(request.getRemoteUser()).getId()));
        }
        else {
            model.addAttribute("requests", this.listRequests.execute());
        }

        return DEFAULT_CONTEXT + "/index";
    }

    /**
     * 
     * @param model interface
     * @param newRequest objeto a ser populado
     * @return tela de abertura de novo solicitação com seu formulário
     */
    @GetMapping({"/new", "/nova-solicitacao"})
    public String openByRequester(Model model, Request newRequest) {
        model.addAttribute("requestTypeList", this.listRequestTypes.execute());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        return DEFAULT_CONTEXT + "/open";
    }

    /**
     * Salva uma requisição
     * @param newRequest objeto preenchido
     * @param ra interface
     * @param files objeto opcional do tipo multipart/data
     * @return para tela de início
     */
    @PostMapping({"/new", "/nova-solicitacao"})
    public String openByRequester(
            Request newRequest,
            RedirectAttributes ra,
            @RequestParam(required = false) MultipartFile[] files) {
        try {
            this.createRequest.execute(newRequest, files);
            ra.addFlashAttribute("success", "Sua solicitação foi criada com sucesso!");
            return "redirect:/inicio";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Salva uma resposta à solicitação
     * @param requestId pk da requisição, do tipo Long
     * @param response objeto preenchido
     * @param files multipart/data com os arquivos
     * @param ra interface
     * @return redireciona para tela de visualização da requisição
     */
    @PostMapping("/{requestId}/response")
    public String createResponse(
        @PathVariable Long requestId, 
        Response response, 
        @RequestParam(required = false) MultipartFile[] files, 
        RedirectAttributes ra) {
        try {
            Request request = this.findRequestById.execute(requestId);

            if(request == null) return "redirect:/404";

            this.createResponse.execute(response, request, files);

            return "redirect:/solicitacoes/" + request.getId();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @param id da requisição
     * @param model interface
     * @return tela de visualização da requisição
     */
    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model, HttpServletRequest httpServletRequest) {
        Request request = this.findRequestById.execute(id);

        if(request == null){
            return "redirect:/404";
        }

        //verifica se o usuário é dono da solicitação
        if(httpServletRequest.isUserInRole("ROLE_COMUM")) {
            if(!request.isRequesterOwner(httpServletRequest.getRemoteUser())) return "redirect:/404";
        }

        model.addAttribute("request", request);
        model.addAttribute("response", new Response());
        if (request.getRequester() != null && !request.getRequester().getUsername().equals("administrator")) 
            model.addAttribute("person", this.findPersonOrLegalEntityByUsername.execute(request.getRequester().getUsername()));
        return DEFAULT_CONTEXT + "/view";
    }
    
}
