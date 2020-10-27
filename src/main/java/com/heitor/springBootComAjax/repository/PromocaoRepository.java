package com.heitor.springBootComAjax.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.heitor.springBootComAjax.domain.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {

	@Transactional(readOnly = false)
	@Modifying
	@Query("update Promocao p set likes = p.likes + 1 where p.id = :id ")
	void updateSomarLikes(@RequestParam(name = "id") Long id);
	
	@Query("select p.likes from Promocao p where id = :id")
	int findLikesById(@RequestParam(name = "id") Long id);
}
