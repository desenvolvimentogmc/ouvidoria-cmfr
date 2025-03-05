package br.com.gmc.ouvidoria.usecase.department;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.DepartmentGateway;
import br.com.gmc.ouvidoria.entity.model.Department;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see DepartmentGateway
 * @see Department
 */
@Service
public class ListDepartments {
	
	private final DepartmentGateway gateway;

	public ListDepartments(DepartmentGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public List<Department> execute() {
		return this.gateway.findAll();
	}

}
