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

import com.intiformation.entity.BienALouer;
import com.intiformation.repository.IBienALouerDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class BienALouerWsREST {

	// ========== DAO =========================//

	@Autowired
	private IBienALouerDao bienALouerDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param bienALouerDao
	 */
	public void setBienALouerDao(IBienALouerDao bienALouerDao) {
		this.bienALouerDao = bienALouerDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL BienALouers ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/bienALouer/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienALouer/get-all", method = RequestMethod.GET)

	public List<BienALouer> getAllBienALouerBdd() {
		System.out.println("Je suis dans getAllBienALouerBdd ws REST ");
		return bienALouerDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id bienALouer ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un bienALouer dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/bienALouer/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienALouer/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<BienALouer> bienALoueryId(@PathVariable("id") int pIdBienALouer) {

		return bienALouerDao.findById(pIdBienALouer);
	}// end bienALoueryId

	// ===========================================================//
	//======= save (ajouter+modifier) bienALouer =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un bienALouer la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/bienALouer/save (POST)
	 * 
	 */
	@RequestMapping(value = "/bienALouer/save", method = RequestMethod.POST)
	public void saveBienALouer(@RequestBody BienALouer pBienALouer) {
		bienALouerDao.save(pBienALouer);
	}// end saveBienALouer


	// ===========================================================//
	// =========== delete bienALouer ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un bienALouer dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/bienALouer/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/bienALouer/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteBienALouer(@PathVariable("id") int pIdBienALouer) {

		bienALouerDao.deleteById(pIdBienALouer);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteBienALouer
}// end class