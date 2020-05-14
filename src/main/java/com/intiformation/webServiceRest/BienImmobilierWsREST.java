package com.intiformation.webServiceRest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intiformation.entity.BienAAcheter;
import com.intiformation.entity.BienImmobilier;
import com.intiformation.repository.IBienImmobilerDao;
import com.intiformation.toolsEntities.TypeBien;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
@CrossOrigin(origins = "http://localhost:4200")
public class BienImmobilierWsREST {

	// ========== DAO =========================//

	@Autowired
	private IBienImmobilerDao bienImmobilierDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param bienImmobilierDao
	 */
	public void setBienImmobilierDao(IBienImmobilerDao bienImmobilierDao) {
		this.bienImmobilierDao = bienImmobilierDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL BienImmobiliers ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/bienImmobilier/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienImmobilier/get-all", method = RequestMethod.GET)

	public List<BienImmobilier> getAllBienImmobilierBdd() {
		System.out.println("Je suis dans getAllBienImmobilierBdd ws REST ");
		return bienImmobilierDao.findAll();
	}// end getAllFonctionnairesBdd

	// ===========================================================//
	// =========== get type by id bienImmobilier ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup le type d'un bienImmobilier dans la
	 * bdd url d'accès :
	 * http://localhost:8080/spring-rest/bienImmobilier/get-type-by-id/{id} (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienImmobilier/get-type-by-id/{id}", method = RequestMethod.GET)
	public TypeBien bienImmobilieryType(@PathVariable("id") int pIdBienImmobilier) {

		String typeBien = "";
		System.out.println("|" + bienImmobilierDao.getTypeBienById(pIdBienImmobilier).toString() + "|");
		if (bienImmobilierDao.getTypeBienById(pIdBienImmobilier).toString()
				.equals("class com.intiformation.entity.BienAAcheter")) {
			typeBien = "achat";
		} else {
			typeBien = "location";
		}

		System.out.println("\n\n type =" + typeBien);
		TypeBien type = new TypeBien(typeBien);
		System.out.println(type);
		return type;
	}// end bienImmobilieryId

	// ===========================================================//
	// =========== get by id bienImmobilier ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un bienImmobilier dans la bdd url
	 * d'accès : http://localhost:8080/spring-rest/bienImmobilier/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienImmobilier/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<BienImmobilier> bienImmobilieryId(@PathVariable("id") int pIdBienImmobilier) {

		return bienImmobilierDao.findById(pIdBienImmobilier);
	}// end bienImmobilieryId

	// ===========================================================//
	// ======= save (ajouter+modifier) bienImmobilier =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un bienImmobilier la méthode
	 * reçoit les données en JSON et les transforme en objet java via l'api jackson
	 * la transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/bienImmobilier/save (POST)
	 * 
	 */
	@RequestMapping(value = "/bienImmobilier/save", method = RequestMethod.POST)
	public void saveBienImmobilier(@RequestBody BienImmobilier pBienImmobilier) {
		bienImmobilierDao.save(pBienImmobilier);
	}// end saveBienImmobilier

	// ===========================================================//
	// =========== delete bienImmobilier ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un bienImmobilier dans la
	 * bdd. Url d'accès :
	 * http://localhost:8080/spring-rest/bienImmobilier/delete/{id} (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienImmobilier/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteBienImmobilier(@PathVariable("id") int pIdBienImmobilier) {

		bienImmobilierDao.deleteById(pIdBienImmobilier);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteBienImmobilier

	// ===========================================================//
	// =========== get by Proprio ===============================//
	// ===========================================================//
	@RequestMapping(value = "/bienImmobilier/get-by-idProprietaire/{id}", method = RequestMethod.GET)
	public List<BienImmobilier> bienImmobilerByIdProprio(@PathVariable("id") int id) {

		return bienImmobilierDao.findByProprietaireId(id);

		
	}// end bienImmobilerByIdProprio
}// end class