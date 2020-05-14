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
import com.intiformation.entity.Visite;
import com.intiformation.repository.IConseillerDao;
import com.intiformation.repository.IContratDAO;
import com.intiformation.repository.IVisiteDao;

@RestController // declare la classe comme webservice
@RequestMapping("/spring-rest") //URL du web service rest
@CrossOrigin(origins="http://localhost:4200")
public class ConseillerWsREST {

	// ========== DAO =========================//

	@Autowired
	private IConseillerDao conseillerDao;
	@Autowired
	private IVisiteDao visiteDao;
	
	@Autowired
	private IContratDAO contratDAO;
	
	// ============ SETTER ====================//

	/**
	 * Setter pour injection spring
	 * 
	 * @param conseillerDao
	 */
	public void setConseillerDao(IConseillerDao conseillerDao) {
		this.conseillerDao = conseillerDao;
	}
	public void setVisiteDao(IVisiteDao visiteDao) {
		this.visiteDao = visiteDao;
	}

	public void setContratDAO(IContratDAO contratDAO) {
		this.contratDAO = contratDAO;
	}


	// ========Méthodes (Services) à exposer dans le WS===========//


	// ===========================================================//
	// =========== Liste ALL Conseillers ====================//
	// ===========================================================//
	/**
	 * Méthode ou Service à exposer dans notre WS. Elle récupère la liste des biens
	 * dans la Bdd. renvoie la liste des biens en JSON. url d'accès :
	 * http://localhost:8080/spring-rest/conseiller/get-all
	 * 
	 * @return
	 */
	@RequestMapping(value = "/conseiller/get-all", method = RequestMethod.GET)

	public List<Conseiller> getAllConseillerBdd() {
		System.out.println("Je suis dans getAllConseillerBdd ws REST ");
		return conseillerDao.findAll();
	}// end getAllFonctionnairesBdd
	// ===========================================================//
	// =========== get by id conseiller ======================//
	// ===========================================================//

	/**
	 * Méthode exposée dans le ws pour la recup d'un conseiller dans la bdd url
	 * d'accès :
	 * http://localhost:8080/spring-rest/conseiller/get-by-id/{id}
	 * (GET)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/conseiller/get-by-id/{id}", method = RequestMethod.GET)
	public Optional<Conseiller> conseilleryId(@PathVariable("id") int pIdConseiller) {

		return conseillerDao.findById(pIdConseiller);
	}// end conseilleryId

	// ===========================================================//
	//======= save (ajouter+modifier) conseiller =============//
	// ===========================================================//

	/**
	 * méthode exposée dans le ws rest pour l'ajout d'un conseiller la méthode reçoit
	 * les données en JSON et les transforme en objet java via l'api jackson la
	 * transfo est assurée avec l'annotation @RequestBody url d'accès :
	 * http://localhost:8080/spring-rest/conseiller/save (POST)
	 * 
	 */
	@RequestMapping(value = "/conseiller/save", method = RequestMethod.POST)
	public void saveConseiller(@RequestBody Conseiller pConseiller) {
		conseillerDao.save(pConseiller);
	}// end saveConseiller


	// ===========================================================//
	// =========== delete conseiller ===============================//
	// ===========================================================//
	/**
	 * Méthode exposée dans le ws pour la suppression d'un conseiller dans la bdd. Url
	 * d'accès :
	 * http://localhost:8080/spring-rest/conseiller/delete/{id}
	 * (DELETE)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/conseiller/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteConseiller(@PathVariable("id") int pIdConseiller) {

		Conseiller conseillerASuppr = conseillerDao.findById(pIdConseiller).get();
		for(Visite visite:conseillerASuppr.getListeVisite()) {
			visite.setClient(null);
			visiteDao.save(visite);
		}
		
		for(Contrat contrat:conseillerASuppr.getListeContrats()) {
			contrat.setClient(null);
			contratDAO.save(contrat);
		}
		conseillerDao.deleteById(pIdConseiller);

		// def de la reponse à renvoyer au client
		/**
		 * Renvoi d'un true => suppression ok renvoi d'un code 200 OK
		 */
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}// end deleteConseiller
}// end class