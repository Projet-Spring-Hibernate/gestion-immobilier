package com.intiformation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité pour la classe ClasseStandard. 
 * 
 * @author Marie
 *
 */
@Entity(name = "classe_standard")
@Table(name = "classes_standards")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
public class ClasseStandard implements Serializable{

	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_classe")
	private int idClasse;

	@Column(name = "code_classe")
	private String codeClasse;

	@Column(name = "typeDeBien")
	private String typeDeBien;

	@Column(name = "utilisation")
	private String utilisation;

	@Column(name = "offre")
	private String offre;

	@Column(name = "prix_max")
	private double prixMax;

	@Column(name = "superficie_min")
	private double superficieMin;

	// Association avec la classe BienImmobilier
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy="classeStandard")
	@JsonBackReference("bienImmobilier-classe") 
	private List<BienImmobilier> listeBienImmobiliers= new ArrayList<>();

	// Association avec la classe Client
	@ManyToMany(mappedBy = "listeClasseStandard", cascade = CascadeType.PERSIST)
	@JsonBackReference("client-classe")
	private List<Client> listeClient= new ArrayList<>();



	//_______________Constructeurs_______________ //
	/**
	 * > Ctor sans l'Id
	 * 
	 * @param typeDeBien
	 * @param utilisation
	 * @param offre
	 * @param prixMax
	 * @param superficieMin
	 * @param bienImmobilier
	 * @param client
	 */
	public ClasseStandard(String codeClasse, String typeDeBien, String utilisation, String offre, double prixMax,
			double superficieMin) {
		super();
		this.codeClasse = codeClasse;
		this.typeDeBien = typeDeBien;
		this.utilisation = utilisation;
		this.offre = offre;
		this.prixMax = prixMax;
		this.superficieMin = superficieMin;
	}


	//________________ Méthodes ____________________//
	
	
	/**
	 * Permet d'ajouter un bien immobilier à la liste des bien d'une classe standard et cette à classe standard au bien immobilier.
	 * @param personne
	 */
	public void addBienImmobilier(BienImmobilier bienImmobilier) {
		this.listeBienImmobiliers.add(bienImmobilier);
		bienImmobilier.setClasseStandard(this);
	}
	
	/**
	 * Permet d'ajouter un client à la liste des clients d'une classe standard et cette à classe standard au client.
	 * @param personne
	 */
	public void addClient(Client client) {
		this.listeClient.add(client);
		List<ClasseStandard> liste =client.getListeClasseStandard();
		liste.add(this);
		client.setListeClasseStandard(liste);
	}
	
	
	@Override
	public String toString() {
		return "ClasseStandard [idClasse=" + idClasse + ", codeClasse=" + codeClasse + ", typeDeBien=" + typeDeBien
				+ ", utilisation=" + utilisation + ", offre=" + offre + ", prixMax=" + prixMax + ", superficieMin="
				+ superficieMin + ", listeBienImmobiliers=" + listeBienImmobiliers + ", listeClient=" + listeClient
				+ "]";
	}



}// end class
