package br.com.gmc.ouvidoria.infrastructure.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping({"/charts","/graficos"})
public class ChartsController {

    @GetMapping
    public String index() {
        return "pages/charts/index";
    }
    
    
}
