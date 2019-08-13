package com.loja.comercio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.comercio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long >{

}
