package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class CountRequestsByStatus {

    private final RequestGateway gateway;

    public CountRequestsByStatus(RequestGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * @param status enum, representando o status da requisição
     * @return quantidade de requisições por status
     */
    public Long execute(Status status) {
        return this.gateway.countByStatus(status);
    }
}
