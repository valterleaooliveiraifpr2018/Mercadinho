package com.loja.lojaVirtual.controller.admin;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.loja.lojaVirtual.model.Funcionario;
import com.loja.lojaVirtual.repository.FuncionarioRepository;

@Controller 
public class FuncionarioController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping("/administrativo/funcionarios/listar")
	public ModelAndView buscarTodos() {
		
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
		mv.addObject("estados", funcionarioRepository.findAll());
		
		return mv;
	}
//	@GetMapping("/administrativo/funcionarios/listar")
//	public ModelAndView buscarNome(String nome) {
//		
//		ModelAndView mv = new ModelAndView("/estadoLista");
//		mv.addObject("estados", repository.buscarPorNome(nome));
//		
//		return mv;
//	}
	
	@GetMapping("/editarFuncionario/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		Funcionario e = funcionario.get();	
		
		return cadastrar(e);
	}
	
	@GetMapping("/removerEstado/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		Funcionario e = funcionario.get();
		funcionarioRepository.delete(e);	
		
		return buscarTodos();
	}
	
	
	
	@GetMapping("/administrativo/funcionarios/cadastro")
	public ModelAndView cadastrar(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	
	@PostMapping("/administrativo/funcionarios/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario,BindingResult result) {
		
		if (result.hasErrors()) {
//			System.out.println("teste" + result.getAllErrors());
			return cadastrar(funcionario);
		} 
		funcionarioRepository.saveAndFlush(funcionario);
		return cadastrar(new Funcionario());
	}
}	