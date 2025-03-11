package br.com.gmc.ouvidoria.usecase.request;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.infrastructure.dto.RequestsBySubjectDTO;

@Service
public class CountRequestsByRequestType {
    
    private final RequestGateway gateway;

    public CountRequestsByRequestType(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public List<RequestsBySubjectDTO> execute() {
        List<Object[]> results = this.gateway.countByRequestType();

        return results.stream().map(row -> new RequestsBySubjectDTO((String) row[0], (Long) row[1])).toList();
    }

}
