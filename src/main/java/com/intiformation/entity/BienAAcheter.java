package com.intiformation.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="bienAcheter")
@DiscriminatorValue("a_acheter")
@Data // ajoute les getters et setters dans le byteCode
@AllArgsConstructor
@NoArgsConstructor
public class BienAAcheter extends BienImmobilier{
	
	//_________________Propriétés_______________ //
	
	@Column(name="etat")
	private String etat;
	
	@Column(name="prix")
	private double prix;

	
	// _______________Constructeurs_______________ //
	/**
	 * Constructeur sans l'id
	 * @param statut
	 * @param dateSoumissionAgence
	 * @param revenuCadastral
	 * @param dateMiseADisposition
	 * @param photo
	 * @param etat
	 * @param prix
	 */
	public BienAAcheter(String statut, String dateSoumissionAgence, String revenuCadastral,
			String dateMiseADisposition, String photo,String typeBien, String utilisation, double superficie, String etat, double prix) {
		super(statut, dateSoumissionAgence, revenuCadastral, dateMiseADisposition, photo, typeBien, utilisation, superficie);
		this.etat = etat;
		this.prix = prix;
	}



	
	//________________ Méthodes ____________________//

	

	@Override
	public String toString() {
		return "BienImmobilier [idBien=" + this.getIdBien() + ", statut=" + this.getStatut() + ", dateSoumissionAgence="
				+ this.getDateSoumissionAgence() + ", revenuCadastral=" + this.getRevenuCadastral() + ", dateMiseADisposition="
				+ this.getDateMiseADisposition() + ", photo=" + this.getPhoto() + ", typeBien=" + this.getTypeBien() + ", utilisation=" + this.getUtilisation()
				+ ", superficie=" + this.getSuperficie() +", etat=" + etat + ", prix=" + prix+ "]";
	}


}//end class
