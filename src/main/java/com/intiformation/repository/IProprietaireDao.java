package com.intiformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intiformation.entity.Proprietaire;


public interface IProprietaireDao extends JpaRepository<Proprietaire, Integer>{

}
