package br.com.gmc.ouvidoria.infrastructure.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import br.com.gmc.ouvidoria.entity.model.Department;
import br.com.gmc.ouvidoria.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.Request;
import br.com.gmc.ouvidoria.entity.model.User;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Request
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query(value = "SELECT * FROM tb_requests WHERE requester_username = :username ORDER BY id :order LIMIT :limit", nativeQuery = true)
    List<Request> findByRequesterUsernameAndLimitOrderByDesc(
        @Param("username") String username,
        @Param("limit") Integer limit,
        @Param("order") String order
    );

    List<Request> findByStatusAndRequestType_Department(Status status, Department department);

    Long countByStatus(Status status);

    @Query(value = "select count(*) as pf from tb_requests tr inner join tb_users ts on ts.username = tr.requester_username left join tb_legal_entities tle on tle.user_username = tr.requester_username left join tb_persons tp on tp.user_username = tr.requester_username where cnpj is not null", nativeQuery = true)
    Long countByLegalEntity();

    @Query(value = "select count(*) as pf from tb_requests tr inner join tb_users ts on ts.username = tr.requester_username left join tb_legal_entities tle on tle.user_username = tr.requester_username left join tb_persons tp on tp.user_username = tr.requester_username where cpf is not null", nativeQuery = true)
    Long countByNaturalPerson();

    List<Request> findAllByRequester_Username(String username);

    List<Request> findAllByRequester_UsernameAndStatus(String requester, Status status);

    List<Request> findAllByRequester_UsernameAndStatusNot(String requester, Status status);

    List<Request> findAllByRequestType_Department_IdAndStatusNot(Integer id, Status status);

    List<Request> findAllByStatusNot(Status status);

    List<Request> findAllByRequestType_Department_Id(Integer id);

    Long countRequestsByRequestType_Department_IdAndStatus(Integer departmentId, Status status);

    List<Request> findAllByStatus(Status status);

    Request findByProtocolAndRequester(String protocol, User requester);

    @Query(value = "SELECT tbt.title, COUNT(tr.id) as count " +
               "FROM tb_request_types tbt " +
               "LEFT JOIN tb_requests tr ON tr.request_type_id = tbt.id " +
               "GROUP BY tbt.title", nativeQuery = true)
    List<Object[]> countByRequestType();

    @Query(value = "SELECT " +
            "DATE_FORMAT(created_at, '%Y-%m') AS month, " +
            "COUNT(CASE WHEN status = 'OPEN' THEN 1 ELSE NULL END) AS open_requests, " +
            "COUNT(CASE WHEN status = 'UNDER_ANALYSIS' THEN 1 ELSE NULL END) AS under_analysis_requests, " +
            "COUNT(CASE WHEN status = 'ANSWERED' THEN 1 ELSE NULL END) AS answered_requests, " +
            "COUNT(CASE WHEN status = 'CLOSED' THEN 1 ELSE NULL END) AS closed_requests, " +
            "COUNT(CASE WHEN status = 'APPEAL' THEN 1 ELSE NULL END) AS appeal_requests, " +
            "COUNT(id) AS total_requests " +
            "FROM tb_requests " +
            "WHERE created_at BETWEEN :startDate AND :endDate " +
            "GROUP BY month " +
            "ORDER BY month", nativeQuery = true)
    List<Map<String, Object>> findRequestsByMonthAndStatus(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query(value = """
            SELECT
			    YEAR(created_at) AS year,
			    COUNT(CASE WHEN status = 'OPEN' THEN 1 ELSE NULL END) AS open_requests,
			    COUNT(CASE WHEN status = 'UNDER_ANALYSIS' THEN 1 ELSE NULL END) AS under_analysis_requests,
			    COUNT(CASE WHEN status = 'ANSWERED' THEN 1 ELSE NULL END) AS answered_requests,
			    COUNT(CASE WHEN status = 'CLOSED' THEN 1 ELSE NULL END) AS closed_requests,
			    COUNT(CASE WHEN status = 'APPEAL' THEN 1 ELSE NULL END) AS appeal_requests,
			    COUNT(id) AS total_requests
			FROM
			    tb_requests
			WHERE
			    created_at >= DATE_FORMAT(NOW() - INTERVAL 3 YEAR, '%Y-01-01')
			    AND created_at < DATE_FORMAT(NOW() + INTERVAL 1 YEAR, '%Y-01-01')
			GROUP BY
			    year
			ORDER BY
			    year;	
        """, nativeQuery = true)
	List<Map<String, Object>> findYearlyReportRequests();
}
