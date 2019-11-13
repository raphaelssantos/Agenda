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

import com.example.agenda.model.Contato;
import com.example.agenda.repository.Contatos;

@RestController
@RequestMapping("/api/contatos")
public class ApiContatoController {

	@Autowired
	private Contatos contatos;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Contato> listaPessoas() {
		return contatos.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Contato> getContato(@PathVariable("id") Long id) {
		return this.contatos.findById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Contato>> listar() {
		return new ResponseEntity<List<Contato>>(new ArrayList<Contato>(contatos.findAll()), 
				HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeContato(@PathVariable("id") Long id) {
		Optional<Contato> c = contatos.findById(id);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		contatos.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public  ResponseEntity<?> saveContato(@RequestBody Contato contato) {
		return new ResponseEntity<Contato> (contatos.save(contato), HttpStatus.OK);
	}

}
