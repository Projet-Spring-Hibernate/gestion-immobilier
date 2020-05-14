package com.intiformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.entity.Conseiller;



public interface IConseillerDao extends JpaRepository<Conseiller, Integer>{
	
	public Conseiller findByEmail(String email);

}
