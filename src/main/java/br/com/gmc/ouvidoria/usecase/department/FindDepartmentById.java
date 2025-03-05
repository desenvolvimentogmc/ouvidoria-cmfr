package br.com.gmc.ouvidoria.usecase.department;

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
public class FindDepartmentById {
	
	private final DepartmentGateway gateway;

	public FindDepartmentById(DepartmentGateway gateway) {
		super();
		this.gateway = gateway;
	}
	
	public Department execute(Integer departmentId) {
		return this.gateway.findById(departmentId);
	}

}
