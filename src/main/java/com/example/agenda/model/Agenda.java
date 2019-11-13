package com.example.agenda.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.example.agenda.model.Agenda;
import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("deprecation")
@Entity
public class Agenda  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O nome � obrigat�rio!")
	@Size(max = 30, message = "O nome n�o pode conter mais de 30 caracteres!")
	private String nome;
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
	private Set<Contato> contatos;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Set<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}
	
	
	
	
}
