package com.example.agenda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.agenda.model.Contato;
import com.example.agenda.repository.Contatos;
import com.example.agenda.repository.Agendas;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private Contatos contatos; 
	
	@Autowired
	private Agendas agendas;

	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaContatos");
		mv.addObject(new Contato());
		mv.addObject("agendas",agendas.findAll());
		mv.addObject("contatos", contatos.findAll());
		return (mv);
	}

	@PostMapping
	public String salvar(Contato contato) {
		this.contatos.save(contato);
		return "redirect:/contatos";
	}

	@RequestMapping(value ="/excluir/{id}")
	public String excluirContatoByPathVariable(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
	{
		this.contatos.deleteById(id);
		return "redirect:/contatos";
	}
	
	@RequestMapping(value ="/alterar/{id}")
	public ModelAndView alterarContatoByPathVariable(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("ListaContato");
		Contato contato = contatos.getOne(id);
		mv.addObject(contato);
		mv.addObject("contatos", contatos.findAll());
		return (mv);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
