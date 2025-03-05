package br.com.gmc.ouvidoria.entity.gateway;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Response;
import br.com.gmc.ouvidoria.infrastructure.repository.ResponseRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see ResponseRepository
 * @see Response
 */
@Component
public class ResponseGateway {
	
	private final ResponseRepository repository;

	public ResponseGateway(ResponseRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Response saveOrUpdate(Response newRepsonse) {
		return this.repository.save(newRepsonse);
	}
	
	public List<Response> findAll() {
		return this.repository.findAll();
	}
	
	public Response findById(Long responseId) {
		return this.repository.findById(responseId).orElse(null);
	}

    public Object[] countFromTheLastTenDays() {
		return this.repository.countFromTheLastTenDays();
    }
}
