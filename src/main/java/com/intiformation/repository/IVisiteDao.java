package com.intiformation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.entity.Visite;



public interface IVisiteDao extends JpaRepository<Visite, Integer>{

	public List<Visite> findByConseillerId(int id);
	public List<Visite> findByClientId(int id);
	public List<Visite> findByBienImmobilierIdBien(int id);

	
}
