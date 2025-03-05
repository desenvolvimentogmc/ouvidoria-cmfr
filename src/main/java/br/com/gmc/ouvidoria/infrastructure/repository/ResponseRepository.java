package br.com.gmc.ouvidoria.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.gmc.ouvidoria.entity.model.Response;

/**
 * @author thiago-ribeiro
 * @version 1.0
 * @since 1.0
 * @see Response
 */
@Repository
public interface ResponseRepository extends JpaRepository<Response, Long>{

    @Query(value = "select date(created_at), count(*) from tb_responses where created_at is not null and created_at >= CURDATE() - interval 7 day group by 1 order by 1", nativeQuery = true)
    Object[] countFromTheLastTenDays();
}
