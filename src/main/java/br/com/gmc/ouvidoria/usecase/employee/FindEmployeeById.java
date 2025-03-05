package br.com.gmc.ouvidoria.usecase.employee;

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
public class FindEmployeeById {
	
	private final EmployeeGateway gateway;

	public FindEmployeeById(EmployeeGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public Employee execute(Integer id) {
		return this.gateway.findById(id);
	}
	
}
