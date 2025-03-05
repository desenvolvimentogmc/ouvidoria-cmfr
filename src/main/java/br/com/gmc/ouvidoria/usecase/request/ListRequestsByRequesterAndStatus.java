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
 */
@Service
public class ListRequestsByRequesterAndStatus {

    private final RequestGateway gateway;

    public ListRequestsByRequesterAndStatus(RequestGateway gateway) {
        this.gateway = gateway;
    }

    public List<Request> execute(String requester, Status status) {
        return this.gateway.findAllByRequesterAndStatus(requester, status);
    }
}
