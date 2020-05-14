package com.intiformation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.entity.Contrat;


public interface IContratDAO extends JpaRepository<Contrat, Integer>{

public  List<Contrat> findByConseillerId(int id);
	
}
