package br.com.gmc.ouvidoria.infrastructure.repository;

import java.util.List;

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
}
