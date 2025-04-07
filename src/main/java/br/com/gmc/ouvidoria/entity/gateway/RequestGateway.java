package br.com.gmc.ouvidoria.entity.gateway;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import br.com.gmc.ouvidoria.entity.model.Department;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.stereotype.Component;

import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.infrastructure.repository.RequestRepository;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Request
 * @see RequestRepository
 * @see Status
 */
@Component
public class RequestGateway {
	
	private final RequestRepository repository;

	public RequestGateway(RequestRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Request saveOrUpdate(Request newRequest) {
		return this.repository.save(newRequest);
	}
	
	public List<Request> findAll() {
		return this.repository.findAll();
	}
	
	public Request findById(Long requestId) {
		return this.repository.findById(requestId).orElse(null);
	}

	public List<Request> findByRequesterUsernameAndLimitOrderBy(String username, Integer limit, String order) {
		return this.repository.findByRequesterUsernameAndLimitOrderByDesc(username, limit, order);
	}

    public List<Request> findByStatusAndDepartment(Status status, Department department) {
		return this.repository.findByStatusAndRequestType_Department(status, department);
    }

    public Long countByStatus(Status status) {
		return this.repository.countByStatus(status);
    }

	public Long countByLegalEntity() {
		return this.repository.countByLegalEntity();
	}

	public Long countByNaturalPerson() {
		return this.repository.countByNaturalPerson();
	}

    public List<Request> findAllByRequester(String requester) {
		return this.repository.findAllByRequester_Username(requester);
    }

	public List<Request> findAllByRequesterAndStatus(String requester, Status status) {
		return this.repository.findAllByRequester_UsernameAndStatus(requester, status);
	}

	public List<Request> findAllByRequesterAndExcludingStatus(String requester, Status status) {
		return this.repository.findAllByRequester_UsernameAndStatusNot(requester, status);
	}

	public List<Request> findAllByDepartmentAndStatusNot(Status status, Integer departmentId) {
		return this.repository.findAllByRequestType_Department_IdAndStatusNot(departmentId, status);
	}

	public List<Request> findAllByStatusNot(Status status) {
		return this.repository.findAllByStatusNot(status);
	}

    public List<Request> findAllByDepartment(Integer departmentId) {
		return this.repository.findAllByRequestType_Department_Id(departmentId);
    }

	public Long countByDepartmentAndStatus(Integer departmentId, Status status) {
		return this.repository.countRequestsByRequestType_Department_IdAndStatus(departmentId, status);
	}

    public List<Request> findAllByStatus(Status status) {
		return this.repository.findAllByStatus(status);
    }

    public Request findByProtocolAndAnonymous(String protocol) {
        return this.repository.findByProtocolAndRequester(protocol, null);
    }

    public List<Object[]> countByRequestType() {
        return this.repository.countByRequestType();
    }

	public List<Map<String, Object>> findMonthlyReportByYear(LocalDateTime startDate, LocalDateTime endDate) {
		return this.repository.findRequestsByMonthAndStatus(startDate, endDate);
	}

	public List<Map<String, Object>> findYearlyReport() {
		return this.repository.findYearlyReportRequests();
	}
}
