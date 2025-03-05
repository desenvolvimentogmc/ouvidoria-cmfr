package br.com.gmc.ouvidoria.usecase.employee;

import br.com.gmc.ouvidoria.entity.model.Role;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class CreateEmployee {

	private final EmployeeGateway gateway;

	public CreateEmployee(EmployeeGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public Employee execute(Employee employee) {
		employee.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		employee.getUser().encryptPassword();
		return this.gateway.saveOrUpdate(employee);
	}

	public Employee execute(Employee employee, String role) {
		employee.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
		employee.getUser().encryptPassword();
		employee.getUser().addRole(new Role(role));
		return this.gateway.saveOrUpdate(employee);
	}
	
}
