package br.com.gmc.ouvidoria.infrastructure.repository;

import br.com.gmc.ouvidoria.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.Employee;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Employee
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    Employee findByUser(User user);

    Employee findByUser_Username(String userUsername);
}
