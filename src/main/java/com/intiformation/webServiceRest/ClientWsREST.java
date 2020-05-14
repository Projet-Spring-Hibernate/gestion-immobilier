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

import com.intiformation.entity.Client;
import com.intiformation.repository.IClientDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class ClientWsREST {

	// ========== DAO =========================//

	@Autowired
	private IClientDao clientDao;

	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param clientDao
	 */
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}

	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Clients ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/client/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/client/get-all", method = RequestMethod.GET)

	public List<Client> getAllClientBdd() {
		System.out.println("Je suis dans getAllClientBdd ws REST ");
		return clientDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id client ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un client dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/client/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/client/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Client> clientyId(@PathVariable("id") int pIdClient) {

		return clientDao.findById(pIdClient);
	}// end clientyId

	// ===========================================================//
	//======= save (ajouter+modifier) client =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un client la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/client/save (POST)
	 * 
	 */
	@RequestMapping(value = "/client/save", method = RequestMethod.POST)
	public void saveClient(@RequestBody Client pClient) {
		clientDao.save(pClient);
	}// end saveClient


	// ===========================================================//
	// =========== delete client ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un client dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/client/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/client/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteClient(@PathVariable("id") int pIdClient) {

		clientDao.deleteById(pIdClient);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteClient
}// end class