package br.com.qparceria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.qparceria.domain.Mate;

@Repository
public interface MatchRepository extends JpaRepository<Mate, Integer>{

	@Query("SELECT obj FROM Mate obj WHERE obj.id.user.id = :user_id AND obj.id.activity.active = true")
	@Transactional(readOnly=true)
	List<Mate> findAllProducedOfUser(@Param("user_id") Integer idUser);

	@Query("SELECT obj FROM Mate obj WHERE obj.id.activity.owner.id = :user_id AND obj.id.activity.active = true")
	@Transactional(readOnly=true)
	List<Mate> findAllReceivedOfUser(@Param("user_id") Integer idUser);

	@Query("SELECT obj FROM Mate obj WHERE obj.id.activity.id = :activity_id")
	@Transactional(readOnly=true)
	List<Mate> findMatchesOfActivity(@Param("activity_id") Integer idActivity);
	
	@Query("SELECT obj FROM Mate obj WHERE obj.id.activity.id = :activity_id AND obj.id.user.id = :user_id")
	@Transactional(readOnly=true)
	List<Mate> getMatchOfUserLoggedOnAct(@Param("activity_id") Integer idActivit,
										 @Param("user_id") Integer idUser);

}
