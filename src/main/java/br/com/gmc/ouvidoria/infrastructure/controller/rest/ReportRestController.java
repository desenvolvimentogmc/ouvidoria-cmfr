package br.com.gmc.ouvidoria.infrastructure.controller.rest;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gmc.ouvidoria.usecase.request.CreateMonthlyReportByYear;
import br.com.gmc.ouvidoria.usecase.request.CreateYearlyReport;

@RestController
@RequestMapping("/api/reports")
public class ReportRestController {
	
	private final CreateMonthlyReportByYear createMonthlyReportByYear;
	private final CreateYearlyReport createYearlyReport;

	public ReportRestController(CreateMonthlyReportByYear createMonthlyReportByYear, CreateYearlyReport createYearlyReport) {
		this.createMonthlyReportByYear = createMonthlyReportByYear;
		this.createYearlyReport = createYearlyReport;
	}
	
	@GetMapping("/monthly")
	public ResponseEntity<?> monthly(@RequestParam(required = false) Integer year) {
		
		if(year == null) {
			year = Year.now().getValue();
		}
		
		LocalDateTime firstDay = getFirstDayOfTheYearAsLocalDateTime(year);
		LocalDateTime lastDay = getLastDayOfTheYearAsLocalDateTime(year);
		
		return ResponseEntity.ok(this.createMonthlyReportByYear.execute(firstDay, lastDay));
	}
	
	@GetMapping("/yearly")
	public ResponseEntity<?> yearly(@RequestParam(required = false) Integer year) {
		return ResponseEntity.ok(this.createYearlyReport.execute());
	}
	
	private LocalDateTime getFirstDayOfTheYearAsLocalDateTime(Integer year) {
		return Year.of(year).atDay(1).atStartOfDay();
	}
	
	private LocalDateTime getLastDayOfTheYearAsLocalDateTime(Integer year) {
		return Year.of(year).atMonth(12).atDay(31).atTime(23, 59, 59);
	}
	
}
