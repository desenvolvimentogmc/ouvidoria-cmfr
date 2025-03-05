package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class CountRequestsByDepartmentAndStatus {

    private final RequestGateway gateway;

    public CountRequestsByDepartmentAndStatus(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public Long execute(Integer departmentId, Status status) {
        return this.gateway.countByDepartmentAndStatus(departmentId, status);
    }
}
