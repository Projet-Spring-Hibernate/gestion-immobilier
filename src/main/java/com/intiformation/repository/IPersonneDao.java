package com.intiformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.entity.Personne;


public interface IPersonneDao extends JpaRepository<Personne, Integer>{

}
