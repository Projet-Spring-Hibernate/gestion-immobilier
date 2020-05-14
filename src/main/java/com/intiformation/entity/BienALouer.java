package com.intiformation.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bienALouer")
@DiscriminatorValue("a_louer")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
public class BienALouer extends BienImmobilier {

	// _________________Propriétés_______________ //

	@Column(name = "caution")
	private double caution;

	@Column(name = "loyer")
	private double loyer;

	@Column(name = "charges")
	private double charges;

	@Column(name = "typeBail")
	private String typeBail;

	@Column(name = "garniture")
	private String garniture;

	// _______________Constructeurs_______________ //
	/**
	 * Constructeur sans id
	 * 
	 * @param statut
	 * @param dateSoumissionAgence
	 * @param revenuCadastral
	 * @param dateMiseADisposition
	 * @param photo
	 * @param etat
	 * @param caution
	 * @param loyer
	 * @param charges
	 * @param typeBail
	 * @param garniture
	 */
	public BienALouer(String statut, String dateSoumissionAgence, String revenuCadastral, String dateMiseADisposition,
			String photo,String typeBien, String utilisation, double superficie, String etat, double caution, double loyer, double charges, String typeBail,
			String garniture) {
		super(statut, dateSoumissionAgence, revenuCadastral, dateMiseADisposition, photo, typeBien, utilisation, superficie);
		this.caution = caution;
		this.loyer = loyer;
		this.charges = charges;
		this.typeBail = typeBail;
		this.garniture = garniture;
	}

	// ________________ Méthodes ____________________//

	@Override
	public String toString() {
		return "BienImmobilier [idBien=" + this.getIdBien() + ", statut=" + this.getStatut() + ", dateSoumissionAgence="
				+ this.getDateSoumissionAgence() + ", revenuCadastral=" + this.getRevenuCadastral()
				+ ", dateMiseADisposition=" + this.getDateMiseADisposition() + ", photo=" + this.getPhoto()
				+ ", typeBien=" + this.getTypeBien() + ", utilisation=" + this.getUtilisation()
				+ ", superficie=" + this.getSuperficie() + ", caution=" + caution + ", loyer=" + loyer + ", charges=" + charges + ", typeBail=" + typeBail
				+ ", garniture=" + garniture + ", proprietaire=" + this.getProprietaire().getId() + ", classeStandard="
				+ this.getClasseStandard().getCodeClasse() + ", adresse=" + this.getAdresse().getIdAdresse()
				+ ", contrat=" + this.getContrat().getIdContrat() + "]";
	}



}// end class
