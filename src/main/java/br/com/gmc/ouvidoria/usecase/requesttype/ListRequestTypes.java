package br.com.gmc.ouvidoria.usecase.requesttype;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestTypeGateway;
import br.com.gmc.ouvidoria.entity.model.RequestType;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestTypeGateway
 */
@Service
public class ListRequestTypes {
    
    private final RequestTypeGateway gateway;

    public ListRequestTypes(RequestTypeGateway gateway) {
        this.gateway = gateway;
    }

    public List<RequestType> execute() {
        return this.gateway.findAll();
    }

}
