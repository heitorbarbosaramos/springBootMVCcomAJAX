package com.heitor.springBootComAjax.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.heitor.springBootComAjax.domain.Promocao;

public interface PromocaoRepository extends JpaRepository<Promocao, Long> {
	
	@Query("select count(p.id) as count, max(p.dataCadastro) as lastDate from Promocao p where p.dataCadastro > :data")
	Map<String, Object> findUltimaPromcaoByDataCadastro(@Param(value = "data") LocalDateTime data);
	
	@Query("select p.dataCadastro from Promocao p")
	Page<LocalDateTime> findUltimaDataPromocao(Pageable pageRequest);
	
	Page<Promocao> findByPreco(BigDecimal preco, Pageable pageRequest);
	
	@Query("select p from Promocao p where"
			+ " p.site like %:search% or "
			+ " p.titulo like %:search% or"
			+ " p.categoria.titulo like %:search%")
	Page<Promocao> findByTituloOrSiteOrCategoria(@RequestParam(name = "search") String search, Pageable pageRequest);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("update Promocao p set likes = p.likes + 1 where p.id = :id ")
	void updateSomarLikes(@RequestParam(name = "id") Long id);
	
	@Query("select p.likes from Promocao p where id = :id")
	int findLikesById(@RequestParam(name = "id") Long id);
	
	@Query("select distinct p.site from Promocao p where p.site like %:site%")
	List<String> findBySiteTermo(@RequestParam(name = "site") String site);
	
	@Query("select p from Promocao p where p.site like %:site%")
	Page<Promocao> findBySitePage(@Param("site") String site, Pageable pageRequest);
}
