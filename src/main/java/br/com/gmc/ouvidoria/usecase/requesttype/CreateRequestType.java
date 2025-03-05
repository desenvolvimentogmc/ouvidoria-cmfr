package br.com.gmc.ouvidoria.usecase.requesttype;

import br.com.gmc.ouvidoria.entity.gateway.RequestTypeGateway;
import br.com.gmc.ouvidoria.entity.model.RequestType;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestTypeGateway
 * @see RequestType
 */
@Service
public class CreateRequestType {

    private final RequestTypeGateway gateway;

    public CreateRequestType(RequestTypeGateway requestTypeGateway) {
        this.gateway = requestTypeGateway;
    }

    public RequestType execute(RequestType requestType) {
        return this.gateway.saveOrUpdate(requestType);
    }
}
