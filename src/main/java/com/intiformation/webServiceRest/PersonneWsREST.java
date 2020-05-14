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

import com.intiformation.entity.Personne;
import com.intiformation.repository.IPersonneDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class PersonneWsREST {

	// ========== DAO =========================//

	@Autowired
	private IPersonneDao personneDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param personneDao
	 */
	public void setPersonneDao(IPersonneDao personneDao) {
		this.personneDao = personneDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Personnes ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/personne/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/get-all", method = RequestMethod.GET)

	public List<Personne> getAllPersonneBdd() {
		System.out.println("Je suis dans getAllPersonneBdd ws REST ");
		return personneDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id personne ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un personne dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/personne/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Personne> personneyId(@PathVariable("id") int pIdPersonne) {

		return personneDao.findById(pIdPersonne);
	}// end personneyId

	// ===========================================================//
	//======= save (ajouter+modifier) personne =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un personne la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/personne/save (POST)
	 * 
	 */
	@RequestMapping(value = "/personne/save", method = RequestMethod.POST)
	public void savePersonne(@RequestBody Personne pPersonne) {
		personneDao.save(pPersonne);
	}// end savePersonne


	// ===========================================================//
	// =========== delete personne ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un personne dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/personne/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/personne/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deletePersonne(@PathVariable("id") int pIdPersonne) {

		personneDao.deleteById(pIdPersonne);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deletePersonne
}// end class