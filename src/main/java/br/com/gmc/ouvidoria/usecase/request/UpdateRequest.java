package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class UpdateRequest {

    private final RequestGateway gateway;

    public UpdateRequest(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public Request execute(Request request) {
        return this.gateway.saveOrUpdate(request);
    }
}
