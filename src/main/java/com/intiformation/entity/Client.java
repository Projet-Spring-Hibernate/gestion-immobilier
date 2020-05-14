package com.intiformation.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="client")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("client")
public class Client extends Personne {
	// _________________Propriétés_______________ //
	@OneToMany(cascade = CascadeType.ALL, mappedBy="client", orphanRemoval=true)
	@JsonBackReference("contrat-client")
	private List<Contrat> listContrat;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="client", orphanRemoval=true)
	@JsonBackReference("visite-client")
	private List<Visite> listeVisite;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name="client_classe_standard",
	joinColumns= @JoinColumn(name="client_id",referencedColumnName="id_personne"),
	inverseJoinColumns= @JoinColumn(name="classeStandard_id",referencedColumnName="id_classe"))
	//@JsonManagedReference("client-classe")
	private List<ClasseStandard> listeClasseStandard =new ArrayList<>();

	//_______________Constructeurs_______________ //
	/**
	 * Constructeur sans id
	 * @param nom
	 * @param telephonePerso
	 * @param telephonePro
	 */
	public Client(String nom, String telephonePerso, String telephonePro) {
		super(nom, telephonePerso, telephonePro);

	};
	
	//________________ Méthodes ____________________//
	
	
	/**
	 * Permet d'ajouter un contrat à la liste des contrats d'un client et ce client au contrat
	 * @param personne
	 */
	public void addContrat(Contrat contrat) {
		this.listContrat.add(contrat);
		contrat.setClient(this);
	}
	
	/**
	 * Permet d'ajouter une visite à la liste des visites d'un client et ce client à la visite
	 * @param personne
	 */
	public void addVisite(Visite visite) {
		this.listeVisite.add(visite);
		visite.setClient(this);
	}
	
	/**
	 * Permet d'ajouter unclasse standard à la liste des classes d'un client et d'ajouter le client à ka liste des clients de la classe standard
	 * @param personne
	 */
	public void addClasseStandard(ClasseStandard classeStandard) {
		this.listeClasseStandard.add(classeStandard);
		List<Client> liste =classeStandard.getListeClient();
		liste.add(this);
		classeStandard.setListeClient(liste);
	}
	
	
	
	
	@Override
	public String toString() {
		return "Personne [id=" + this.getId() + ", nom=" + this.getNom() + ", telephonePerso=" + this.getTelephonePerso() + ", telephonePro="
				+ this.getTelephonePro() + ", adresse=" + this.getAdresse().getIdAdresse() + "]";
	}

	

	
}//end class
