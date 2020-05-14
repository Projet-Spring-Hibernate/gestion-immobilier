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
import com.intiformation.repository.IBienAAcheterDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class BienAAcheterWsREST {

	// ========== DAO =========================//

	@Autowired
	private IBienAAcheterDao bienAAcheterDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param bienAAcheterDao
	 */
	public void setBienAAcheterDao(IBienAAcheterDao bienAAcheterDao) {
		this.bienAAcheterDao = bienAAcheterDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL BienAAcheters ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/bienAAcheter/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienAAcheter/get-all", method = RequestMethod.GET)

	public List<BienAAcheter> getAllBienAAcheterBdd() {
		System.out.println("Je suis dans getAllBienAAcheterBdd ws REST ");
		return bienAAcheterDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id bienAAcheter ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un bienAAcheter dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/bienAAcheter/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienAAcheter/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<BienAAcheter> bienAAcheteryId(@PathVariable("id") int pIdBienAAcheter) {

		return bienAAcheterDao.findById(pIdBienAAcheter);
	}// end bienAAcheteryId

	// ===========================================================//
	//======= save (ajouter+modifier) bienAAcheter =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un bienAAcheter la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/bienAAcheter/save (POST)
	 * 
	 */
	@RequestMapping(value = "/bienAAcheter/save", method = RequestMethod.POST)
	public void saveBienAAcheter(@RequestBody BienAAcheter pBienAAcheter) {
		bienAAcheterDao.save(pBienAAcheter);
	}// end saveBienAAcheter


	// ===========================================================//
	// =========== delete bienAAcheter ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un bienAAcheter dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/bienAAcheter/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienAAcheter/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteBienAAcheter(@PathVariable("id") int pIdBienAAcheter) {

		bienAAcheterDao.deleteById(pIdBienAAcheter);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteBienAAcheter
}// end class