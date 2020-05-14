package com.intiformation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.intiformation.entity.BienImmobilier;


public interface IBienImmobilerDao extends JpaRepository<BienImmobilier, Integer>{

	
	@Query("SELECT TYPE(b) FROM bien b WHERE b.idBien= ?1")
	public Class getTypeBienById(int id);
	
	public List<BienImmobilier> findByProprietaireId(int id);
	
}//end interface
