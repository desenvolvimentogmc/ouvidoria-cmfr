package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import br.com.gmc.ouvidoria.entity.gateway.ResponseGateway;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class CountResponsesFromTheLastTenDays {

    private final ResponseGateway gateway;

    public CountResponsesFromTheLastTenDays(ResponseGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * @return array de labels e values com a data e o valor obtido
     */
    public Object[] execute() {

        return this.gateway.countFromTheLastTenDays();
    }
}
