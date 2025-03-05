package br.com.gmc.ouvidoria.usecase.requesttype;

import br.com.gmc.ouvidoria.entity.gateway.RequestTypeGateway;
import br.com.gmc.ouvidoria.entity.model.RequestType;
import org.springframework.stereotype.Service;

@Service
public class FindRequestTypeById {

    private final RequestTypeGateway gateway;

    public FindRequestTypeById(RequestTypeGateway gateway) {
        this.gateway = gateway;
    }

    public RequestType execute(Integer id){
        return this.gateway.findById(id);
    }
}
