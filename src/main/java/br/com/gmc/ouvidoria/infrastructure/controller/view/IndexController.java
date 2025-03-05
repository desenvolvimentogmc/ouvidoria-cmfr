package br.com.gmc.ouvidoria.infrastructure.controller.view;

import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByPersonType;
import br.com.gmc.ouvidoria.usecase.request.CountRequestsByStatus;
import br.com.gmc.ouvidoria.usecase.request.CountResponsesFromTheLastTenDays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/")
public class IndexController {

	@Value("${spring.application.release-date}")
	private LocalDate releaseDate;

	private final CountRequestsByStatus countRequestsByStatus;
	private final CountRequestsByPersonType countRequestsByPersonType;
	private final CountResponsesFromTheLastTenDays countResponsesFromTheLastTenDays;

    public IndexController(
			CountRequestsByStatus countRequestsByStatus,
			CountRequestsByPersonType countRequestsByPersonType,
			CountResponsesFromTheLastTenDays countResponsesFromTheLastTenDays) {
        this.countRequestsByStatus = countRequestsByStatus;
        this.countRequestsByPersonType = countRequestsByPersonType;
        this.countResponsesFromTheLastTenDays = countResponsesFromTheLastTenDays;
    }

    /**
	 * 
	 * @return landingpage
	 */
	@GetMapping
	public String index(Model model) {
		model.addAttribute("releaseDate", releaseDate);
		model.addAttribute("responsesCount", this.countResponsesFromTheLastTenDays.execute());
		model.addAttribute("naturalPersonCount", this.countRequestsByPersonType.execute(false));
		model.addAttribute("legalEntityCount", this.countRequestsByPersonType.execute(true));
		model.addAttribute(String.valueOf(Status.OPEN), this.countRequestsByStatus.execute(Status.OPEN));
		model.addAttribute(String.valueOf(Status.APPEAL), this.countRequestsByStatus.execute(Status.APPEAL));
		model.addAttribute(String.valueOf(Status.CLOSED), this.countRequestsByStatus.execute(Status.CLOSED));
		return "pages/index";
	}

	/**
	 * @return array com labels e values
	 */
	@GetMapping(value = "/graph-responses", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object[] response() {
		return this.countResponsesFromTheLastTenDays.execute();
	}

}
