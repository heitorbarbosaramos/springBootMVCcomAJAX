package com.heitor.springBootComAjax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heitor.springBootComAjax.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
