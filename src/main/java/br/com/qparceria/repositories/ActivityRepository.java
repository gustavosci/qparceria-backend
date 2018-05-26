package br.com.qparceria.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
	
	@Query("SELECT obj FROM Activity obj WHERE obj.owner.id = :owner_id AND obj.active = true")
	@Transactional(readOnly=true)
	List<Activity> findAllByOwner(@Param("owner_id") Integer idOwner);
	
	@Query("SELECT obj "
			+ "FROM Activity obj "
			+ "WHERE obj.sport.id = :sport_id AND "
			+ "obj.cityStart.id = :citystart_id AND "
			+ "obj.distance <= :maxDistance AND "
			+ "obj.averageSpeed <= :maxAverage AND "
			+ "obj.active = true")
	@Transactional(readOnly=true)
	List<Activity> search(@Param("sport_id") Integer idsport, 
						  @Param("citystart_id") Integer idcitystart,
						  @Param("maxDistance") BigDecimal maxDistance,
						  @Param("maxAverage") BigDecimal maxAverage);

}
