package com.intiformation.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité pour la classe Visite.
 * 
 * @author Marie
 *
 */
@Entity(name = "visite")
@Table(name = "visites")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class Visite implements Serializable{

	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_visite")
	private int idVisite;

	@Column(name = "date_visite")
	private String dateDeVisite;

	@Column(name = "heure_visite")
	private String heureDeVisite;

	// Association avec la classe BienImmobilier
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bien_id", referencedColumnName = "id_bien")
	//@JsonManagedReference("visite-bienImmobilier")
	private BienImmobilier bienImmobilier;

	// Association avec la classe Client
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", referencedColumnName = "id_personne")
	//@JsonManagedReference("visite-client")
	private Client client;

	// Association avec la classe Client
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "conseiller_id", referencedColumnName = "id_personne")
	//@JsonManagedReference("visite-conseiller")
	private Conseiller conseiller;

	// _______________Constructeurs_______________ //
	/**
	 * 	> Ctor sans l'Id.
	 * 
	 * @param dateDeVisite
	 * @param heureDeVisite
	 * @param bienImmobilier
	 * @param client
	 * @param conseiller
	 */
	public Visite(String dateDeVisite, String heureDeVisite) {
		super();
		this.dateDeVisite = dateDeVisite;
		this.heureDeVisite = heureDeVisite;

	}


	
	// ________________ Méthodes ____________________//
	
	/**
	 * Permet d'attribuer bien à une visite et d'ajouter la visite à la liste des visites du bien.
	 * @param personne
	 */
	public void addBienImmobilier(BienImmobilier bienImmobilier) {
		this.bienImmobilier=bienImmobilier;
		List<Visite> liste =bienImmobilier.getListeVisite();
		liste.add(this);
		bienImmobilier.setListeVisite(liste);
	}
	
	/**
	 * Permet d'attribuer client à une visite et d'ajouter la visite à la liste des visites du client.
	 * @param personne
	 */
	public void addClient(Client client) {
		this.client=client;
		List<Visite> liste =client.getListeVisite();
		liste.add(this);
		client.setListeVisite(liste);
	}
	
	/**
	 * Permet d'attribuer bien à une visite et d'ajouter la visite à la liste des visites du bien.
	 * @param personne
	 */
	public void addConseiller(Conseiller conseiller) {
		this.conseiller=conseiller;
		List<Visite> liste =conseiller.getListeVisite();
		liste.add(this);
		conseiller.setListeVisite(liste);
	}
	

	@Override
	public String toString() {
		return "Visite [idVisite=" + idVisite + ", dateDeVisite=" + dateDeVisite + ", heureDeVisite=" + heureDeVisite
				+ ", bienImmobilier=" + bienImmobilier.getIdBien() + ", client=" + client.getId() + ", conseiller=" + conseiller.getId() + "]";
	}



	
}// end classe
