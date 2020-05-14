package com.intiformation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intiformation.entity.Client;


//@Repository("clientDao")
public interface IClientDao extends JpaRepository<Client, Integer>{

}
