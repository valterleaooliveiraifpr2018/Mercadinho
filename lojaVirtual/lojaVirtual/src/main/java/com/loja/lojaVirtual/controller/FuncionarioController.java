package com.loja.lojaVirtual.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.loja.lojaVirtual.model.Funcionario;
import com.loja.lojaVirtual.repository.FuncionarioRepository;

@Controller 
public class FuncionarioController {
	
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping("/administrativo/funcionarios/cadastro")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	@GetMapping("/administrativo/funcionarios/listar")
	public String acessarLista() {
		return "administrativo/funcionario/lista";
		
	}
	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario,BindingResult result) {
		if (result.hasErrors()) {
			return cadastrar(funcionario);
		} 
		funcionarioRepository.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());
	}
}	