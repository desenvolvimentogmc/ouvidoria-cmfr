package br.com.gmc.ouvidoria.infrastructure.controller.view;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.enums.Category;
import br.com.gmc.ouvidoria.usecase.request.CreateAnonymousRequest;
import br.com.gmc.ouvidoria.usecase.request.FindAnonymousRequestByProtocol;
import br.com.gmc.ouvidoria.usecase.requesttype.ListRequestTypes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/manifestacao-anonima")
public class AnonymousRequestController {
    
    private static final String DEFAULT_CONTEXT = "pages/anonymous";

    private final CreateAnonymousRequest createAnonymousRequest;
    private final ListRequestTypes listRequestTypes;
    private final FindAnonymousRequestByProtocol findAnonymousRequestByProtocol;

    public AnonymousRequestController(CreateAnonymousRequest createAnonymousRequest, 
                                        ListRequestTypes listRequestTypes, 
                                        FindAnonymousRequestByProtocol findAnonymousRequestByProtocol) {
        this.createAnonymousRequest = createAnonymousRequest;
        this.listRequestTypes = listRequestTypes;
        this.findAnonymousRequestByProtocol = findAnonymousRequestByProtocol;
    }

    @GetMapping
    public String form(Model model) {
        model.addAttribute("request", new Request());
        model.addAttribute("categories", Arrays.asList(Category.values()));
        model.addAttribute("requestTypeList", this.listRequestTypes.execute());
        return DEFAULT_CONTEXT + "/form";
    }

    @PostMapping
    public String post(Request request, RedirectAttributes ra) {
        try {
            request = this.createAnonymousRequest.execute(request);
            ra.addFlashAttribute("success", "Solicitação realizada com sucesso!");
            ra.addFlashAttribute("protocol", request.getProtocol());
            ra.addFlashAttribute("message", "Por gentileza, salve seu protocolo para que seja possível acompanhar a sua solicitação.");
        } catch (Exception e) {
            // TODO: handle exception
        }
        return "redirect:/manifestacao-anonima";
    }

    @GetMapping("/search")
    public String getByProtocol(@RequestParam String protocol, Model model, RedirectAttributes ra) {

        Request request = this.findAnonymousRequestByProtocol.execute(protocol);
        
        if(request == null) {
            ra.addFlashAttribute("info", "Protocolo não localizado.");
            return "redirect:/";
        }

        model.addAttribute("request", request);
        return DEFAULT_CONTEXT + "/view";
    }
    
    
    
    
}
