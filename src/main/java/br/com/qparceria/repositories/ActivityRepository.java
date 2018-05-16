package br.com.qparceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
	
	@Query("SELECT obj FROM Activity obj WHERE obj.owner.id = :owner_id")
	@Transactional(readOnly=true)
	List<Activity> findAllByOwner(@Param("owner_id") Integer idOwner);
}
