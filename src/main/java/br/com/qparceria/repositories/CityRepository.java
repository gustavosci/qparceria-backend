package br.com.qparceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

	@Query("SELECT obj FROM City obj WHERE obj.uf.id = :uf_id ORDER BY obj.name")
	@Transactional(readOnly=true)
	List<City> findCities(@Param("uf_id") Integer ufId);

}
