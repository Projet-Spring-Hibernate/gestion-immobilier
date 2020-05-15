package com.intiformation.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "contrat")
@Table(name = "contrats")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
public class Contrat implements Serializable{

	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contrat")
	private int idContrat;
	
	@Column(name = "prix")
	private double prix;
	@Column(name = "date")
	private String date;
	@Column(name = "numero_ref")
	private String numeroRef;
	
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bien_id", referencedColumnName = "id_bien")
	//@JsonManagedReference("contrat-bienImmobilier")
	private BienImmobilier bienImmobilier;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "client_id", referencedColumnName = "id_personne")
	//@JsonManagedReference("contrat-client")
	private Client client;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "conseiller_id", referencedColumnName = "id_personne")
	//@JsonManagedReference("contrat-conseiller")
	private Conseiller conseiller;
	
	//_______________Constructeurs_______________ //
	/**
	 * Contructeur sans id
	 * @param prix
	 * @param date
	 * @param numeroRef
	 */
	public Contrat(double prix, String date, String numeroRef) {
		super();
		this.prix = prix;
		this.date = date;
		this.numeroRef = numeroRef;
	}


	
	
	//________________ Méthodes ____________________//
	
	/**
	 * Permet d'attribuer un bienImmo à un contrat et ce contrat au bienImmo.
	 * @param personne
	 */
	public void addBienImmobiler(BienImmobilier bienImmobilier) {
		this.bienImmobilier=bienImmobilier;
		bienImmobilier.setContrat(this);
	}
	
	/**
	 * Permet d'attribuer un client à un contrat et le contrat à la liste des contrats du client
	 * @param personne
	 */
	public void addClient(Client client) {
		this.client=client;
		List<Contrat> liste =client.getListContrat();
		liste.add(this);
		client.setListContrat(liste);
	}
	
	/**
	 * Permet d'attribuer un conseiller à un contrat et le contrat à la liste des contrats du conseiller
	 * @param personne
	 */
	public void addConseiller(Conseiller conseiller) {
		this.conseiller=conseiller;
		List<Contrat> liste =conseiller.getListeContrats();
		liste.add(this);
		conseiller.setListeContrats(liste);
	}
	
	@Override
	public String toString() {
		return "Contrat [idContrat=" + idContrat + ", prix=" + prix + ", date=" + date + ", numeroRef=" + numeroRef+ "]";
	}




	
}//end class
