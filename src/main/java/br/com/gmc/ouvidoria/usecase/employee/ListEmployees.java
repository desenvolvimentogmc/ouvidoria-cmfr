package br.com.gmc.ouvidoria.usecase.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.EmployeeGateway;
import br.com.gmc.ouvidoria.entity.model.Employee;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see EmployeeGateway
 * @see Employee
 */
@Service
public class ListEmployees {
	
	private final EmployeeGateway gateway;

	public ListEmployees(EmployeeGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public List<Employee> execute() {
		return this.gateway.findAll();
	}
	
}
