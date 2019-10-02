package com.loja.lojaVirtual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.lojaVirtual.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
	List<Funcionario> findByNome(String nome);

}
