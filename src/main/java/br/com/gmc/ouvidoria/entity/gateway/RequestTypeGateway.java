package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.RequestType;
import br.com.gmc.ouvidoria.infrastructure.repository.RequestTypeRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see RequestTypeRepository
 * @see RequestType
 */
@Component
public class RequestTypeGateway {
	
	private final RequestTypeRepository repository;

	public RequestTypeGateway(RequestTypeRepository repository) {
		super();
		this.repository = repository;
	}
	
	public RequestType saveOrUpdate(RequestType newRequestType) {
		return this.repository.save(newRequestType);
	}
	
	public List<RequestType> findAll() {
		return this.repository.findAll();
	}
	
	public RequestType findById(Integer requestTypeId) {
		return this.repository.findById(requestTypeId).orElse(null);
	}

}
