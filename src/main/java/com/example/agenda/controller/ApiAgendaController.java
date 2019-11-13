package com.example.agenda.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.agenda.model.Agenda;
import com.example.agenda.repository.Agendas;

@RestController
@RequestMapping("/api/agendas")
public class ApiAgendaController {
	
	@Autowired
	private Agendas agendas;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Agenda> listaAgendas() {
		return agendas.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Agenda> getAgenda(@PathVariable("id") Long id) {
		return this.agendas.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Agenda>> listar() {
		return new ResponseEntity<List<Agenda>>(new ArrayList<Agenda>(agendas.findAll()), 
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeCampeonato(@PathVariable("id") Long id) {
		Optional<Agenda> a = agendas.findById(id);
		if (a == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		agendas.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveCampeonato(@RequestBody Agenda agenda) {
		return new ResponseEntity<Agenda> (agendas.save(agenda), HttpStatus.OK);
	}


}
