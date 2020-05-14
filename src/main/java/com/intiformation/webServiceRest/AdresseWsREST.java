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

import com.intiformation.entity.Adresse;
import com.intiformation.repository.IAdresseDAO;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class AdresseWsREST {

	// ========== DAO =========================//

	@Autowired
	private IAdresseDAO adresseDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param adresseDao
	 */
	public void setAdresseDao(IAdresseDAO adresseDao) {
		this.adresseDao = adresseDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Adresses ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/adresse/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/get-all", method = RequestMethod.GET)

	public List<Adresse> getAllAdresseBdd() {
		System.out.println("Je suis dans getAllAdresseBdd ws REST ");
		return adresseDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id adresse ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un adresse dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/adresse/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Adresse> adresseyId(@PathVariable("id") int pIdAdresse) {

		return adresseDao.findById(pIdAdresse);
	}// end adresseyId

	// ===========================================================//
	//======= save (ajouter+modifier) adresse =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un adresse la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/adresse/save (POST)
	 * 
	 */
	@RequestMapping(value = "/adresse/save", method = RequestMethod.POST)
	public void saveAdresse(@RequestBody Adresse pAdresse) {
		adresseDao.save(pAdresse);
	}// end saveAdresse


	// ===========================================================//
	// =========== delete adresse ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un adresse dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/adresse/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adresse/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteAdresse(@PathVariable("id") int pIdAdresse) {

		adresseDao.deleteById(pIdAdresse);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteAdresse
}// end class