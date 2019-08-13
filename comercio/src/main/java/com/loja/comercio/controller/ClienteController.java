package com.loja.comercio.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.loja.comercio.model.Cliente;
import com.loja.comercio.repository.CidadeRepository;
import com.loja.comercio.repository.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository crr;
	@Autowired
	private CidadeRepository cr;
	
	@GetMapping("/cliente")
	public ModelAndView buscarTodos() {
		ModelAndView mv = new ModelAndView("clienteLista");
		mv.addObject("cliente", crr.findAll());
		return mv;
		
	}
	@GetMapping("/addCliente")
	public ModelAndView add(Cliente cliente) {
		ModelAndView mv = new ModelAndView("clienteAdd");
		mv.addObject("cliente", cliente);
		return mv;
		
	}
	@GetMapping("/editarCliente/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = crr.findById(id);
		Cliente e = cliente.get();
		
		return add(e);
	}
	@GetMapping("/removerCliente/{id}")
	public ModelAndView remove(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = crr.findById(id);
		Cliente e = cliente.get();
		crr.delete(e);		
		return buscarTodos();
	}
	
	@PostMapping("/salvarCliente")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			return add(cliente);
		}
		crr.saveAndFlush(cliente);
		return buscarTodos();
	}	
}
