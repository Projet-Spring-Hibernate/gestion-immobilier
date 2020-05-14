package com.intiformation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="conseiller")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("conseiller")
public class Conseiller extends Personne {

	// _________________Propriétés_______________ //
	
	@Column(name="mot_de_passe")
	private String motDePasse;
	
	@Column(name="email")
	private String email;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="conseiller")
	@JsonBackReference("contrat-conseiller")
	private List<Contrat> listeContrats=new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="conseiller")
	@JsonBackReference("visite-conseiller")
	private List<Visite> listeVisite =new ArrayList<>();

	//_______________Constructeurs_______________ //
	/**
	 * Contructeur sans id
	 * @param nom
	 * @param telephonePerso
	 * @param telephonePro
	 * @param email
	 * @param motDePasse
	 */
	public Conseiller(String nom, String telephonePerso, String telephonePro,String email,String motDePasse) {
		super(nom, telephonePerso, telephonePro);
		this.motDePasse = motDePasse;
		this.email = email;
	}


	//________________ Méthodes ____________________//
	
	/**
	 * Permet d'ajouter un contrat à la liste des contrats d'un conseiller et ce conseiller au contrat
	 * @param personne
	 */
	public void addContrat(Contrat contrat) {
		this.listeContrats.add(contrat);
		contrat.setConseiller(this);
	}
	
	
	/**
	 * Permet d'ajouter une visite à la liste des visites d'un conseiller et ce conseiller à la visite
	 * @param personne
	 */
	public void addVisite(Visite visite) {
		this.listeVisite.add(visite);
		visite.setConseiller(this);
	}
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Personne [id=" + this.getId() + ", nom=" + this.getNom() + ", telephonePerso=" + this.getTelephonePerso() + ", telephonePro="
				+ this.getTelephonePro() + ", adresse=" + this.getAdresse().getIdAdresse() +", motDePasse=" + motDePasse + ", email=" + email + ", listeContrats=" + listeContrats
						+ ", listeVisite=" + listeVisite + "]";
	}


	
}//end class
