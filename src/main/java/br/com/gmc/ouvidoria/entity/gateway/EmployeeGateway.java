package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;

import br.com.gmc.ouvidoria.entity.model.User;
import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Employee;
import br.com.gmc.ouvidoria.infrastructure.repository.EmployeeRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Employee
 * @see EmployeeRepository
 */
@Component
public class EmployeeGateway {
	
	private final EmployeeRepository repository;

	public EmployeeGateway(EmployeeRepository repository) {
		super();
		this.repository = repository;
	}
	
	/**
	 * @param newEmployee Employee.class
	 * @return novo funcionário
	 */
	public Employee saveOrUpdate(Employee newEmployee) {
		return this.repository.save(newEmployee);
	}
	
	/**
	 * 
	 * @return lista de funcionários
	 */
	public List<Employee> findAll() {
		return this.repository.findAll();
	}
	
	/**
	 * 
	 * @param employeeId Integer
	 * @return funcionário ou nulo
	 */
	public Employee findById(Integer employeeId) {
		return this.repository.findById(employeeId).orElse(null);
	}

    public Employee findByUser(User user) {
		return this.repository.findByUser(user);
    }

	public Employee findByUser(String username) {
		return this.repository.findByUser_Username(username);
	}
}
