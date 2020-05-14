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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bien")
@Table(name = "biens")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class BienImmobilier implements Serializable {

	// _________________Propriétés_______________ //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_bien")
	private int idBien;

	@Column(name = "statut")
	private String statut;

	@Column(name = "date_soumission_agence")
	private String dateSoumissionAgence;

	@Column(name = "revenu_cadastral")
	private String revenuCadastral;

	@Column(name = "date_mise_a_dispotion")
	private String dateMiseADisposition;

	@Column(name = "photo")
	private String photo;
	
	@Column(name="type_bien")
	private String typeBien;
	
	@Column(name="utilisation")
	private String utilisation;
	
	@Column(name="superficie")
	private double superficie;

	// Association avec Propriétaire
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "proprietaire_id", referencedColumnName = "id_personne")
	//@JsonManagedReference("bienImmobilier-proprietaire")
	private Proprietaire proprietaire;

	// Association avec ClasseStandard
	@ManyToOne(cascade = CascadeType.PERSIST) // A VERIF
	@JoinColumn(name = "classeStandard_id", referencedColumnName = "id_classe")
	//@JsonManagedReference("bienImmobilier-classe")
	private ClasseStandard classeStandard;

	// Association avec Visite
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "bienImmobilier", orphanRemoval = true)
	@JsonBackReference("visite-bienImmobilier")
	private List<Visite> listeVisite;

	// Association avec Adresse
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "adresse_id", referencedColumnName = "id_adresse")
	//@JsonManagedReference("bienImmobilier-adresse")
	private Adresse adresse;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "bienImmobilier" , orphanRemoval = true)
	@JsonBackReference ("contrat-bienImmobilier")
	private Contrat contrat;

	// _______________Constructeurs_______________ //
	/**
	 * Constructeur sans Id sans liaisons
	 * 
	 * @param statut
	 * @param dateSoumissionAgence
	 * @param revenuCadastral
	 * @param dateMiseADisposition
	 * @param photo
	 */
	public BienImmobilier(String statut, String dateSoumissionAgence, String revenuCadastral,
			String dateMiseADisposition, String photo, String typeBien, String utilisation, double superficie) {
		super();
		this.statut = statut;
		this.dateSoumissionAgence = dateSoumissionAgence;
		this.revenuCadastral = revenuCadastral;
		this.dateMiseADisposition = dateMiseADisposition;
		this.photo = photo;
		this.typeBien = typeBien;
		this.utilisation = utilisation;
		this.superficie = superficie;
	}

	/**
	 * Constructeur avec Id sans liaisons
	 * 
	 * @param idBien
	 * @param statut
	 * @param dateSoumissionAgence
	 * @param revenuCadastral
	 * @param dateMiseADisposition
	 * @param photo
	 */
	public BienImmobilier(int idBien, String statut, String dateSoumissionAgence, String revenuCadastral,
			String dateMiseADisposition, String photo, String typeBien, String utilisation, double superficie) {
		super();
		this.idBien = idBien;
		this.statut = statut;
		this.dateSoumissionAgence = dateSoumissionAgence;
		this.revenuCadastral = revenuCadastral;
		this.dateMiseADisposition = dateMiseADisposition;
		this.photo = photo;
		this.typeBien = typeBien;
		this.utilisation = utilisation;
		this.superficie = superficie;
	}

	
	// ________________ Méthodes ____________________//
	
	/**
	 * Permet d'attribuer un proprio à un bien et d'ajouter ce bien à la liste des bien d'un proprio
	 * @param personne
	 */
	public void addProprietaire(Proprietaire proprietaire) {
		this.proprietaire=proprietaire;
		List<BienImmobilier> liste =proprietaire.getListeBienImmobiliers();
		liste.add(this);
		proprietaire.setListeBienImmobiliers(liste);
	}
	
	/**
	 * Permet d'attribuer une classe standard à un bien et d'ajouter ce bien à la liste des bien d'une la classe standard
	 * @param personne
	 */
	public void addClasseStandard(ClasseStandard classeStandard) {
		this.classeStandard=classeStandard;
		List<BienImmobilier> liste =classeStandard.getListeBienImmobiliers();
		liste.add(this);
		classeStandard.setListeBienImmobiliers(liste);
	}
	
	/**
	 * Permet d'ajouter une visite à la liste des visites d'un bien et d'ajouter ce bien à la visite
	 * @param personne
	 */
	public void addVisite(Visite visite) {
		this.listeVisite.add(visite);
		visite.setBienImmobilier(this);
	}
	
	
	/**
	 * Permet d'attribuer une adresse  à un bien et d'ajouter ce bien à l'adresse
	 * @param personne
	 */
	public void addAdresse(Adresse adresse) {
		this.adresse=adresse;
		adresse.setBienImmobilier(this);
	}
	
	/**
	 * Permet d'attribuer un contrat  à un bien et d'ajouter ce bien au contrat
	 * @param personne
	 */
	public void addContrat(Contrat contrat) {
		this.contrat=contrat;
		contrat.setBienImmobilier(this);
	}

	@Override
	public String toString() {
		return "BienImmobilier [idBien=" + idBien + ", statut=" + statut + ", dateSoumissionAgence="
				+ dateSoumissionAgence + ", revenuCadastral=" + revenuCadastral + ", dateMiseADisposition="
				+ dateMiseADisposition + ", photo=" + photo + ", typeBien=" + typeBien + ", utilisation=" + utilisation
				+ ", superficie=" + superficie + ", proprietaire=" + proprietaire.getId() + ", classeStandard=" + classeStandard.getCodeClasse()
				+ ", adresse=" + adresse.getIdAdresse() + ", contrat=" + contrat.getIdContrat() + "]";
	}

	
	

}// end class
