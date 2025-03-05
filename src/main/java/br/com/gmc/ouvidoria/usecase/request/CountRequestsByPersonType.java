package br.com.gmc.ouvidoria.usecase.request;

import br.com.gmc.ouvidoria.entity.gateway.RequestGateway;
import org.springframework.stereotype.Service;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestGateway
 */
@Service
public class CountRequestsByPersonType {

    private final RequestGateway gateway;

    public CountRequestsByPersonType(RequestGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * @param isLegalEntity boolean para buscar por cpf ou cnpj
     * @return quantidade de requisições por tipo de pessoa
     */
    public Long execute(boolean isLegalEntity) {
        if(isLegalEntity) {
            return this.gateway.countByLegalEntity();
        }

        return this.gateway.countByNaturalPerson();
    }

}
