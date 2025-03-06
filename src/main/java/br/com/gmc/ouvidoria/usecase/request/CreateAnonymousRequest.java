package br.com.gmc.ouvidoria.usecase.request;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.enums.Status;
import br.com.gmc.ouvidoria.utils.ProtocolUtils;

@Service
public class CreateAnonymousRequest {

    private final RequestGateway gateway;

    public CreateAnonymousRequest(RequestGateway gateway) {
        this.gateway = gateway;
    }
    
    public Request execute(Request request) {
        request.setRequester(null);
        request.setProtocol(ProtocolUtils.generate());
        request.setStatus(Status.OPEN);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        return this.gateway.saveOrUpdate(request);
    }
    
}
