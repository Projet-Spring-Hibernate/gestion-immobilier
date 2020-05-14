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

import com.intiformation.entity.Visite;
import com.intiformation.repository.IVisiteDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class VisiteWsREST {

	// ========== DAO =========================//

	@Autowired
	private IVisiteDao visiteDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param visiteDao
	 */
	public void setVisiteDao(IVisiteDao visiteDao) {
		this.visiteDao = visiteDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Visites ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/visite/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/visite/get-all", method = RequestMethod.GET)

	public List<Visite> getAllVisiteBdd() {
		System.out.println("Je suis dans getAllVisiteBdd ws REST ");
		return visiteDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id visite ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un visite dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/visite/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/visite/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Visite> visiteyId(@PathVariable("id") int pIdVisite) {

		return visiteDao.findById(pIdVisite);
	}// end visiteyId

	// ===========================================================//
	//======= save (ajouter+modifier) visite =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un visite la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/visite/save (POST)
	 * 
	 */
	@RequestMapping(value = "/visite/save", method = RequestMethod.POST)
	public void saveVisite(@RequestBody Visite pVisite) {
		visiteDao.save(pVisite);
	}// end saveVisite


	// ===========================================================//
	// =========== delete visite ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un visite dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/visite/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/visite/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteVisite(@PathVariable("id") int pIdVisite) {

		visiteDao.deleteById(pIdVisite);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteVisite
	
	// ===========================================================//
	// =========== get by id conseiller ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un visite dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/visite/get-by-idconseiller/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/visite/get-by-idconseiller/{id}", method = RequestMethod.GET)
	public List<Visite> visitebyIdConseiller(@PathVariable("id") int id) {

		return visiteDao.findByConseillerId(id);
	}// end visiteyId
}// end class