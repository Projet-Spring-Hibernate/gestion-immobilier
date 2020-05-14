package com.intiformation.entity;

import java.util.List;

import javax.persistence.CascadeType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="proprietaire")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("proprietaire")
public class Proprietaire extends Personne{

	// _________________Propriétés_______________ //
	//Association avec BienImmobilier
	@OneToMany(cascade= CascadeType.PERSIST, mappedBy="proprietaire" )
	@JsonBackReference("bienImmobilier-proprietaire")
	private  List<BienImmobilier> listeBienImmobiliers;
	
	
	//_______________Constructeurs_______________ //
	/**
	 * Constructeur sans id
	 * @param nom
	 * @param telephonePerso
	 * @param telephonePro
	 */
	public Proprietaire(String nom, String telephonePerso, String telephonePro) {
		super(nom, telephonePerso, telephonePro);
	}

	//________________ Méthodes ____________________//
	
	
	/**
	 * Permet d'ajouter un bien à la liste des biens d'un proprio et ce proprio au bien
	 * @param personne
	 */
	public void addBienImmobilier(BienImmobilier bienImmobilier) {
		this.listeBienImmobiliers.add(bienImmobilier);
		bienImmobilier.setProprietaire(this);
	}
	
	@Override
	public String toString() {
		return "Personne [id=" + this.getId() + ", nom=" + this.getNom() + ", telephonePerso=" + this.getTelephonePerso() + ", telephonePro="
				+ this.getTelephonePro() + ", adresse=" + this.getAdresse().getIdAdresse() + "]";
	}

	public List<BienImmobilier> getListeBienImmobiliers() {
		return listeBienImmobiliers;
	}

	

	

	
	
}//End class
