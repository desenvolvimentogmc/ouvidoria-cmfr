package br.com.gmc.ouvidoria.infrastructure.controller.view;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gmc.ouvidoria.enums.Gender;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping({"/auth", "/acesso"})
public class AuthController {

    private static final String DEFAULT_CONTEXT = "pages/auth";

    /**
     * @return página de autenticação
     */
    @GetMapping
    public String auth() {
        return DEFAULT_CONTEXT + "/login";
    }

    /**
     * 
     * @return página de registro
     */
    @GetMapping({"/new", "/novo-cadastro"})
    public String register(Model model) {
        model.addAttribute("genders", Arrays.asList(Gender.values()));
        return DEFAULT_CONTEXT + "/register";
    }
    
    
    
}
