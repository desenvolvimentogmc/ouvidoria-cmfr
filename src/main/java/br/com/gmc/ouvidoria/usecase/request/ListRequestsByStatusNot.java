package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class ListRequestsByStatusNot {

    private final RequestGateway gateway;

    public ListRequestsByStatusNot(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public List<Request> execute(Status status) {
        return this.gateway.findAllByStatusNot(status);
    }

    
}
