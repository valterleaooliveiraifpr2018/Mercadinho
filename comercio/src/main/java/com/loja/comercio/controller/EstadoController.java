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

import com.loja.comercio.model.Estado;
import com.loja.comercio.repository.EstadoRepository;

@Controller
public class EstadoController {
	
	@Autowired
	private EstadoRepository er;
	
	@GetMapping("/estados")
	public ModelAndView buscarTodos() {
		ModelAndView mv = new ModelAndView("/estadoLista");
		mv.addObject("estados",er.findAll());
		return mv;		
	}
	@GetMapping("/estadoNome")
	public ModelAndView buscarNome(String nome) {
		ModelAndView mv = new ModelAndView("/estadoLista");
		mv.addObject("estados", er.buscarPorNome(nome));
		return mv;
	}
	@GetMapping("/addEstado")
	public ModelAndView add(Estado estado) {
		ModelAndView mv = new ModelAndView("/estadoAdd");
		mv.addObject("estado", estado);
		return mv;
	}
	@GetMapping("/editarEstado/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Optional<Estado> estado = er.findById(id);
		Estado e = estado.get();
		return add(e);
		
	}
	@GetMapping("/removerEstado/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		Optional<Estado> estado = er.findById(id);
		Estado e = estado.get();
		er.delete(e);
		return buscarTodos();
		
	}
	@PostMapping("/salvarEstado")
	public ModelAndView save(@Valid Estado estado,BindingResult result) {
		if(result.hasErrors()) {
			return add(estado);
		}
		er.saveAndFlush(estado);
		return buscarTodos();
		
	}
	
}
