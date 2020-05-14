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

import com.intiformation.entity.ClasseStandard;
import com.intiformation.repository.IClasseStandardDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class ClasseStandardWsREST {

	// ========== DAO =========================//

	@Autowired
	private IClasseStandardDao classeStandardDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param classeStandardDao
	 */
	public void setClasseStandardDao(IClasseStandardDao classeStandardDao) {
		this.classeStandardDao = classeStandardDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL ClasseStandards ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/classeStandard/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/classeStandard/get-all", method = RequestMethod.GET)

	public List<ClasseStandard> getAllClasseStandardBdd() {
		System.out.println("Je suis dans getAllClasseStandardBdd ws REST ");
		return classeStandardDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id classeStandard ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un classeStandard dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/classeStandard/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/classeStandard/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<ClasseStandard> classeStandardyId(@PathVariable("id") int pIdClasseStandard) {

		return classeStandardDao.findById(pIdClasseStandard);
	}// end classeStandardyId

	// ===========================================================//
	//======= save (ajouter+modifier) classeStandard =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un classeStandard la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/classeStandard/save (POST)
	 * 
	 */
	@RequestMapping(value = "/classeStandard/save", method = RequestMethod.POST)
	public void saveClasseStandard(@RequestBody ClasseStandard pClasseStandard) {
		classeStandardDao.save(pClasseStandard);
	}// end saveClasseStandard


	// ===========================================================//
	// =========== delete classeStandard ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un classeStandard dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/classeStandard/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/classeStandard/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteClasseStandard(@PathVariable("id") int pIdClasseStandard) {

		classeStandardDao.deleteById(pIdClasseStandard);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteClasseStandard
}// end class