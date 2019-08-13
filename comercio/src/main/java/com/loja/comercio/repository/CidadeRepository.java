package com.loja.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.comercio.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
}
