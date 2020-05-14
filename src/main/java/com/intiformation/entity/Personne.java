package com.intiformation.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.DiscriminatorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité pour la classe Personne. Classe mère de Proprietaire, Conseiller et Client
 * 
 * @author IN-MP-018
 *
 */
@Entity(name="personne")
@Table(name="personnes")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role", discriminatorType=DiscriminatorType.STRING)
public class Personne implements Serializable{

	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_personne")
	private int id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="telPerso")
	private String telephonePerso;
	
	@Column(name="telPro")
	private String telephonePro;
	
	
	// Association avec Adresse
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "adresse_id", referencedColumnName = "id_adresse")
	//@JsonManagedReference("personne-adresse")
	private Adresse adresse;

	//_______________Constructeurs_______________ //
	/**
	 * Constructeur sans l'id
	 * @param nom
	 * @param telephonePerso
	 * @param telephonePro
	 * @param adresse
	 */
	public Personne(String nom, String telephonePerso, String telephonePro) {
		super();
		this.nom = nom;
		this.telephonePerso = telephonePerso;
		this.telephonePro = telephonePro;
	}

	//________________ Méthodes ____________________//
	
	/**
	 * Permet d'attribuer une adresse  à une personne et d'ajouter cette personne à l'adresse
	 * @param personne
	 */
	public void addAdresse(Adresse adresse) {
		this.adresse=adresse;
		adresse.setPersonne(this);
	}
	
	
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", telephonePerso=" + telephonePerso + ", telephonePro="
				+ telephonePro + ", adresse=" + adresse.getIdAdresse() + "]";
	}

	


	
}//end class
