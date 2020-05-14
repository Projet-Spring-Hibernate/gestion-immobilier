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

import com.intiformation.entity.Proprietaire;
import com.intiformation.repository.IProprietaireDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class ProprietaireWsREST {

	// ========== DAO =========================//

	@Autowired
	private IProprietaireDao proprietaireDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param proprietaireDao
	 */
	public void setProprietaireDao(IProprietaireDao proprietaireDao) {
		this.proprietaireDao = proprietaireDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Proprietaires ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/proprietaire/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/proprietaire/get-all", method = RequestMethod.GET)

	public List<Proprietaire> getAllProprietaireBdd() {
		System.out.println("Je suis dans getAllProprietaireBdd ws REST ");
		return proprietaireDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id proprietaire ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un proprietaire dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/proprietaire/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/proprietaire/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Proprietaire> proprietaireyId(@PathVariable("id") int pIdProprietaire) {

		return proprietaireDao.findById(pIdProprietaire);
	}// end proprietaireyId

	// ===========================================================//
	//======= save (ajouter+modifier) proprietaire =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un proprietaire la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/proprietaire/save (POST)
	 * 
	 */
	@RequestMapping(value = "/proprietaire/save", method = RequestMethod.POST)
	public void saveProprietaire(@RequestBody Proprietaire pProprietaire) {
		proprietaireDao.save(pProprietaire);
	}// end saveProprietaire


	// ===========================================================//
	// =========== delete proprietaire ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un proprietaire dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/proprietaire/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/proprietaire/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProprietaire(@PathVariable("id") int pIdProprietaire) {

		proprietaireDao.deleteById(pIdProprietaire);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteProprietaire
}// end class