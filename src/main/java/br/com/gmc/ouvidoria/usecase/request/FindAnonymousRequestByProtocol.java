package br.com.gmc.ouvidoria.usecase.request;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;

@Service
public class FindAnonymousRequestByProtocol {
    
    private final RequestGateway gateway;

    public FindAnonymousRequestByProtocol(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public Request execute(String protocol) {
        return this.gateway.findByProtocolAndAnonymous(protocol);
    }

}
