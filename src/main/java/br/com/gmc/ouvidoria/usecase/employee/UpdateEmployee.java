package br.com.gmc.ouvidoria.usecase.employee;

import br.com.gmc.ouvidoria.entity.gateway.EmployeeGateway;
import br.com.gmc.ouvidoria.entity.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateEmployee {

    private final EmployeeGateway gateway;

    public UpdateEmployee(EmployeeGateway gateway) {
        this.gateway = gateway;
    }

    public Employee execute(Employee oldEmployee, Employee newEmployee){
        if(oldEmployee == null || newEmployee == null){
            throw new NullPointerException("Employee cannot be null");
        }

        if(oldEmployee.getId() != newEmployee.getId()){
            throw new IllegalArgumentException("Employee Ids doesn't match");
        }

        oldEmployee.setName(newEmployee.getName());
        oldEmployee.setDepartment(newEmployee.getDepartment());
        oldEmployee.setEmployeeId(newEmployee.getEmployeeId());
        oldEmployee.setCpf(newEmployee.getCpf());
        oldEmployee.setUpdatedAt(LocalDateTime.now());
        oldEmployee.getUser().setEnabled(oldEmployee.getUser().isEnabled());
        return this.gateway.saveOrUpdate(oldEmployee);
    }

}
