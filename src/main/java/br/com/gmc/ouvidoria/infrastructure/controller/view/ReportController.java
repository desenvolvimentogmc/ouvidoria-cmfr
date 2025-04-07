package br.com.gmc.ouvidoria.infrastructure.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/reports", "/relatorios"})
public class ReportController {
	
	private static final String DEFAULT_CONTEXT = "pages/reports";
	
	@GetMapping
	public String reports() {
		return DEFAULT_CONTEXT + "/index";
	}
	
	@GetMapping({"/monthly", "/mensal"})
	public String monthly() {
		return DEFAULT_CONTEXT + "/monthly";
	}
	
	@GetMapping({"/yearly", "/anual"})
	public String yearly() {
		return DEFAULT_CONTEXT + "/yearly";
	}
}
