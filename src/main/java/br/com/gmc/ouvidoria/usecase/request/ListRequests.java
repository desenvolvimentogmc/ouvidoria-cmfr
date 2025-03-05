package br.com.gmc.ouvidoria.usecase.request;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class ListRequests {
    
    private final RequestGateway gateway;

    public ListRequests(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public List<Request> execute() {
        return this.gateway.findAll();
    }

    public List<Request> execute(String username, Integer limit, String order) {
        return this.gateway.findByRequesterUsernameAndLimitOrderBy(username, limit, order);
    }
    
}
