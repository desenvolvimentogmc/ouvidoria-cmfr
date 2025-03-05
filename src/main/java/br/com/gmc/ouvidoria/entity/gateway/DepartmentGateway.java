package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Department;
import br.com.gmc.ouvidoria.infrastructure.repository.DepartmentRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see DepartmentRepository
 * @see Department
 */
@Component
public class DepartmentGateway {

	private final DepartmentRepository repository;

	public DepartmentGateway(DepartmentRepository repository) {
		super();
		this.repository = repository;
	}
	
	/**
	 * @param newDepartment departamento a ser salvo
	 * @return novo departamento persistido
	 */
	public Department saveOrUpdate(Department newDepartment) {
		return this.repository.save(newDepartment);
	}
	
	/**
	 * @return lista de departamentos
	 */
	public List<Department> findAll() {
		return this.repository.findAll();
	}
	
	/**
	 * @param departmentId Integer (id do departamento)
	 * @return departamento encontrado ou nulo
	 */
	public Department findById(Integer departmentId) {
		return this.repository.findById(departmentId).orElse(null);
	}
}
