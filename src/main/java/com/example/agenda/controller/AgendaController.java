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

import com.example.agenda.model.Agenda;
import com.example.agenda.repository.Agendas;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Controller
@RequestMapping("/agendas")
public class AgendaController {
	
	
	@Autowired
	private Agendas agendas;


	@RequestMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("ListaAgendas");
		mv.addObject(new Agenda());
		mv.addObject("agendas", agendas.findAll());
		return (mv);
	}

	@PostMapping
	public String salvar(Agenda agenda) {
		this.agendas.save(agenda);
		return "redirect:/agendas";
	}

	@RequestMapping(value ="/excluir/{id}")
	public String excluirAgendaByPathVariable(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
	{
		this.agendas.deleteById(id);
		return "redirect:/agendas";
	}
	
	@RequestMapping(value ="/alterar/{id}")
	public ModelAndView alterarAgendaByPathVariable(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("ListaAgendas");
		Agenda agenda = agendas.getOne(id);
		mv.addObject(agenda);
		mv.addObject("agendas", agendas.findAll());
		return (mv);
	}
	
	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}

}
