package com.example.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.agenda.model.Agenda;

public interface Agendas extends JpaRepository<Agenda, Long> {
	
}