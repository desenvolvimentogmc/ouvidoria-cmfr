package br.com.gmc.ouvidoria.usecase.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;

@Service
public class CreateMonthlyReportByYear {
	
	private final RequestGateway gateway;
	
	public CreateMonthlyReportByYear(RequestGateway requestGateway) {
		this.gateway = requestGateway;
	}
	
	public List<Map<String, Object>> execute(LocalDateTime startDate, LocalDateTime endDate) {
		return this.gateway.findMonthlyReportByYear(startDate, endDate);
	}
	
	

}
