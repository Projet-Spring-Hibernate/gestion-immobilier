package com.intiformation.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author IN-DF-019
 *
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adresse implements Serializable{
	
	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_adresse")
	private int idAdresse;
	
	@Column(name="rue")
	private String rue;
	
	@Column(name="code_postal")
	private String codePostal;
	
	@Column(name="localite")
	private String localite;

	@OneToOne(cascade = CascadeType.PERSIST, mappedBy="adresse")
	@JsonBackReference("personne-adresse")
	private Personne personne;
	
	@OneToOne(cascade = CascadeType.PERSIST, mappedBy="adresse")
	@JsonBackReference("bienImmobilier-adresse")
	private BienImmobilier bienImmobilier;
	
	//_______________Constructeurs_______________ //
	
	public Adresse(String rue, String codePostal, String localite) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.localite = localite;
	}

	//________________ Méthodes ____________________//
	
	/**
	 * Permet d'attribuer une personne à une adresse et cette à adresse à la personne.
	 * @param personne
	 */
	public void addPersonne(Personne personne) {
		this.personne=personne;
		personne.setAdresse(this);
	}
	
	/**
	 * Permet d'attribuer un bien à une adresse et cette à adresse au bien immo.
	 * @param bienImmo
	 */
	public void addBienImmobilier(BienImmobilier bienImmo) {
		this.bienImmobilier=bienImmo;
		bienImmo.setAdresse(this);
	}

	@Override
	public String toString() {
		return "Adresse [idAdresse=" + idAdresse + ", rue=" + rue + ", codePostal=" + codePostal + ", localite="
				+ localite + ", personne=" + personne.getId() + ", bienImmobilier=" + bienImmobilier.getIdBien() + "]";
	}

	

	
	
}//end class
