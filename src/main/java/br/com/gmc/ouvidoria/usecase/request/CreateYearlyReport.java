package br.com.gmc.ouvidoria.usecase.request;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;

@Service
public class CreateYearlyReport {
	
	private final RequestGateway gateway;

	public CreateYearlyReport(RequestGateway gateway) {
		this.gateway = gateway;
	}
	
	public List<Map<String, Object>> execute() {
		return this.gateway.findYearlyReport();
	}
	
	

}
