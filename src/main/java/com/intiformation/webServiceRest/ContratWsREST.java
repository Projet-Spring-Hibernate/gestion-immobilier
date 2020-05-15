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

import com.intiformation.entity.Conseiller;
import com.intiformation.entity.Contrat;
import com.intiformation.repository.IContratDAO;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") // URL du web service rest
@CrossOrigin(origins = "http://localhost:4200")
public class ContratWsREST {

	// ========== DAO =========================//

	@Autowired
	private IContratDAO contratDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param contratDao
	 */
	public void setContratDao(IContratDAO contratDao) {
		this.contratDao = contratDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//

	// ===========================================================//
	// =========== Liste ALL Contrats ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/contrat/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/get-all", method = RequestMethod.GET)

	public List<Contrat> getAllContratBdd() {
		System.out.println("Je suis dans getAllContratBdd ws REST ");
		return contratDao.findAll();
	}// end getAllFonctionnairesBdd
		// ===========================================================//
		// =========== get by id contrat ======================//
		// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un contrat dans la bdd url d'accès
	 * : http://localhost:8080/spring-rest/contrat/get-by-id/{id} (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Contrat> contratyId(@PathVariable("id") int pIdContrat) {

		return contratDao.findById(pIdContrat);
	}// end contratyId

	// ===========================================================//
	// ======= save (ajouter+modifier) contrat =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un contrat la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/contrat/save (POST)
	 * 
	 */
	@RequestMapping(value = "/contrat/save", method = RequestMethod.POST)
	public void saveContrat(@RequestBody Contrat pContrat) {
		contratDao.save(pContrat);
	}// end saveContrat

	// ===========================================================//
	// =========== delete contrat ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un contrat dans la bdd. Url
	 * d'accès : http://localhost:8080/spring-rest/contrat/delete/{id} (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteContrat(@PathVariable("id") int pIdContrat) {

		contratDao.deleteById(pIdContrat);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteContrat

	// ===========================================================//
	// =========== get by Conseiller ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un contrat par son conseiller dans la bdd url d'accès
	 * : http://localhost:8080/spring-rest/contrat/get-by-idconseiller/{id} (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/get-by-idconseiller/{id}", method = RequestMethod.GET)
	public List<Contrat> contratbyIdConseiller(@PathVariable("id") int id) {

		System.out.println(contratDao.findByConseillerId(id));
		return contratDao.findByConseillerId(id);
	}// end 
	
	// ===========================================================//
	// =========== get by client ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un contrat par son client dans la bdd url d'accès
	 * : http://localhost:8080/spring-rest/contrat/get-by-idclient/{id} (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/get-by-idclient/{id}", method = RequestMethod.GET)
	public List<Contrat> contratbyIdClient(@PathVariable("id") int id) {

		return contratDao.findByClientId(id);
	}// end 
	
	// ===========================================================//
	// =========== get by bien ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un contrat par son bien dans la bdd url d'accès
	 * : http://localhost:8080/spring-rest/contrat/get-by-idBien/{id} (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/contrat/get-by-idBien/{id}", method = RequestMethod.GET)
	public Contrat contratbyIdBien(@PathVariable("id") int id) {

		return contratDao.findByBienImmobilierIdBien(id);
	}// end 

}// end class