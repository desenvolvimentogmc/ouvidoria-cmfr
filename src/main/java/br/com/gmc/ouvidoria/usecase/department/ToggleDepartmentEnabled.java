package br.com.gmc.ouvidoria.usecase.department;

import br.com.gmc.ouvidoria.entity.gateway.DepartmentGateway;
import br.com.gmc.ouvidoria.entity.model.Department;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ToggleDepartmentEnabled {

    private final DepartmentGateway gateway;
    private final FindDepartmentById findDepartmentById;

    public ToggleDepartmentEnabled(DepartmentGateway gateway, FindDepartmentById findDepartmentById) {
        this.gateway = gateway;
        this.findDepartmentById = findDepartmentById;
    }

    public void execute(Integer departmentId) {
        Department department = this.findDepartmentById.execute(departmentId);
        if(department == null) {
            throw new EntityNotFoundException("O departamento não foi encontrado");
        }

        department.setEnabled(!department.isEnabled());
        this.gateway.saveOrUpdate(department);
    }

    public void enable(Integer departmentId) {
        Department department = this.findDepartmentById.execute(departmentId);
        if(department == null) {
            throw new EntityNotFoundException("O departamento não foi encontrado");
        }

        department.setEnabled(true);
        this.gateway.saveOrUpdate(department);
    }

    public void disable(Integer departmentId) {
        Department department = this.findDepartmentById.execute(departmentId);
        if(department == null) {
            throw new EntityNotFoundException("O departamento não foi encontrado");
        }

        department.setEnabled(false);
        this.gateway.saveOrUpdate(department);
    }
}
