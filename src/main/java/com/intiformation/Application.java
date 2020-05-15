package com.intiformation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.intiformation.entity.Adresse;
import com.intiformation.entity.BienAAcheter;
import com.intiformation.entity.BienALouer;
import com.intiformation.entity.ClasseStandard;
import com.intiformation.entity.Client;
import com.intiformation.entity.Conseiller;
import com.intiformation.entity.Contrat;
import com.intiformation.entity.Proprietaire;
import com.intiformation.entity.Visite;
import com.intiformation.repository.IAdresseDAO;
import com.intiformation.repository.IBienAAcheterDao;
import com.intiformation.repository.IBienALouerDao;
import com.intiformation.repository.IBienImmobilerDao;
import com.intiformation.repository.IClasseStandardDao;
import com.intiformation.repository.IClientDao;
import com.intiformation.repository.IConseillerDao;
import com.intiformation.repository.IContratDAO;
import com.intiformation.repository.IPersonneDao;
import com.intiformation.repository.IProprietaireDao;
import com.intiformation.repository.IVisiteDao;

@SpringBootApplication
@EntityScan(basePackages = { "com.intiformation.entity" }) // detection des classes entités par spring boot
@EnableJpaRepositories("com.intiformation.repository")
//@ComponentScan(basePackages = {"com.intiformation"})
public class Application implements CommandLineRunner {

	// declaration + injection de la couche dao

	@Autowired
	private IAdresseDAO adresseDao;

	@Autowired
	private IBienAAcheterDao bienAAcheterDao;

	@Autowired
	private IBienALouerDao bienALouer;

	@Autowired
	private IBienImmobilerDao bienImmobilierDao;

	@Autowired
	private IClasseStandardDao classeStandardDao;

	@Autowired
	private IConseillerDao conseillerDao;

	@Autowired
	private IContratDAO contratDao;

	@Autowired
	private IPersonneDao personneDao;

	@Autowired
	private IProprietaireDao proprietaireDao;

	@Autowired
	private IClientDao clientDao;

	@Autowired
	private IVisiteDao visiteDao;

	/**
	 * Methode main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("... Lancement de l'application ...");
		SpringApplication.run(Application.class, args);

	}// end main

	/**
	 * Methode de l'interface CommandLineRunner qu'on va utiliser pour tester la
	 * couche Dao implémentée dans la classe UserRepository La méthode va s'executer
	 * après l'execution de l'application
	 */
	@Override
	public void run(String... args) throws Exception {
		System.out.println("... Dans la methode Run...");

		// ----- Conseillers -------------- //

		Conseiller conseiller1 = new Conseiller("conseiller1", "0123456789", "0698653212", "conseiller1@gmail.com",
				"$2a$10$OxNxmSL7nXjJrlEMvBlyoeU6DJOXnq1nnsxAXPgyg5XasuAON.ptG");
		Conseiller conseiller2 = new Conseiller("conseiller2", "0123456789", "0698653212", "conseiller2@gmail.com",
				"$2a$10$OxNxmSL7nXjJrlEMvBlyoeU6DJOXnq1nnsxAXPgyg5XasuAON.ptG");
		Conseiller conseiller3 = new Conseiller("conseiller3", "0123456789", "0698653212", "conseiller3@gmail.com",
				"$2a$10$OxNxmSL7nXjJrlEMvBlyoeU6DJOXnq1nnsxAXPgyg5XasuAON.ptG");

		// ----- Propriétaires -------------//

		Proprietaire proprietaire1 = new Proprietaire("proprio1", "0123456789", "0698653212");
		Proprietaire proprietaire2 = new Proprietaire("proprio2", "0123456789", "0698653212");
		Proprietaire proprietaire3 = new Proprietaire("proprio3", "0123456789", "0698653212");
		Proprietaire proprietaire4 = new Proprietaire("proprio4", "0123456789", "0698653212");

		// ---- Client --------------------//

		Client client1 = new Client("client1", "0123456789", "0698653212");
		Client client2 = new Client("client2", "0123456789", "0698653212");
		Client client3 = new Client("client3", "0123456789", "0698653212");
		Client client4 = new Client("client4", "0123456789", "0698653212");

		// ---- Adresse pour les personnes ------------------//
		Adresse adresse1 = new Adresse("rue1", "75000", "Paris");
		Adresse adresse2 = new Adresse("rue2", "75000", "Paris");
		Adresse adresse3 = new Adresse("rue3", "75000", "Paris");
		Adresse adresse4 = new Adresse("rue4", "75000", "Paris");
		Adresse adresse5 = new Adresse("rue5", "75000", "Paris");
		Adresse adresse6 = new Adresse("rue6", "75000", "Paris");
		Adresse adresse7 = new Adresse("rue7", "75000", "Paris");
		Adresse adresse8 = new Adresse("rue8", "75000", "Paris");
		Adresse adresse9 = new Adresse("rue9", "75000", "Paris");
		Adresse adresse10 = new Adresse("ru10", "75000", "Paris");
		Adresse adresse11 = new Adresse("rue11", "75000", "Paris");

		// --- Attribution des adresses aux personnes ---------//

		conseiller1.addAdresse(adresse1);
		conseiller2.addAdresse(adresse2);
		conseiller3.addAdresse(adresse3);

		proprietaire1.addAdresse(adresse4);
		proprietaire2.addAdresse(adresse5);
		proprietaire3.addAdresse(adresse6);
		proprietaire4.addAdresse(adresse7);

		client1.addAdresse(adresse8);
		client2.addAdresse(adresse9);
		client3.addAdresse(adresse10);
		client4.addAdresse(adresse11);

		// Ajout des personnes dans la bdd avec leurs adresse

		conseillerDao.save(conseiller1);
		conseillerDao.save(conseiller2);
		conseillerDao.save(conseiller3);

		proprietaireDao.save(proprietaire1);
		proprietaireDao.save(proprietaire2);
		proprietaireDao.save(proprietaire3);
		proprietaireDao.save(proprietaire4);

		clientDao.save(client1);
		clientDao.save(client2);
		clientDao.save(client3);
		clientDao.save(client4);

		// Recup des personnes pour avoir leurs id

		List<Conseiller> listeConseillers = conseillerDao.findAll();

		conseiller1 = listeConseillers.get(0);
		conseiller2 = listeConseillers.get(1);
		conseiller3 = listeConseillers.get(2);

		List<Proprietaire> listeProprietaires = proprietaireDao.findAll();

		proprietaire1 = listeProprietaires.get(0);
		proprietaire2 = listeProprietaires.get(1);
		proprietaire3 = listeProprietaires.get(2);
		proprietaire4 = listeProprietaires.get(3);

		List<Client> listeClients = clientDao.findAll();

		client1 = listeClients.get(0);
		client2 = listeClients.get(1);
		client3 = listeClients.get(2);
		client4 = listeClients.get(3);

		// ------ Classes standards --------------//

		ClasseStandard classeStandardVTminS10maxP300k = new ClasseStandard("VTminS10maxP300k", "terrain", "commerciale",
				"vente", 300000.0, 10.0);
		ClasseStandard classeStandardLMminS2maxP1k = new ClasseStandard("LMminS2maxP1k", "maison", "habitation",
				"location", 1000, 2);
		ClasseStandard classeStandardVMminS3maxP200k = new ClasseStandard("VMminS3maxP200k", "maison", "habitation",
				"vente", 200000.0, 3);
		ClasseStandard classeStandardLSminS1maxP600 = new ClasseStandard("LSminS1maxP600", "studio", "habitation",
				"location", 600, 1);
		ClasseStandard classeStandardVSminS1maxP150k = new ClasseStandard("VSminS1maxP150k", "studio", "habitation",
				"acheter", 150000.0, 1);
		ClasseStandard classeStandardLEminS100maxP2k = new ClasseStandard("LEminS100maxP2k", "entrepot", "commercial",
				"location", 2000, 100);
		ClasseStandard classeStandardVBminS5maxP300k = new ClasseStandard("VBminS5maxP300k", "bureau", "commercial",
				"vente", 300000.0, 5);

		classeStandardDao.save(classeStandardVTminS10maxP300k);
		classeStandardDao.save(classeStandardLMminS2maxP1k);
		classeStandardDao.save(classeStandardVMminS3maxP200k);
		classeStandardDao.save(classeStandardLSminS1maxP600);
		classeStandardDao.save(classeStandardVSminS1maxP150k);
		classeStandardDao.save(classeStandardLEminS100maxP2k);
		classeStandardDao.save(classeStandardVBminS5maxP300k);

		List<ClasseStandard> listeClasseStandards = classeStandardDao.findAll();

		classeStandardVTminS10maxP300k = listeClasseStandards.get(0);
		classeStandardLMminS2maxP1k = listeClasseStandards.get(1);
		classeStandardVMminS3maxP200k = listeClasseStandards.get(2);
		classeStandardLSminS1maxP600 = listeClasseStandards.get(3);
		classeStandardVSminS1maxP150k = listeClasseStandards.get(4);
		classeStandardLEminS100maxP2k = listeClasseStandards.get(5);
		classeStandardVBminS5maxP300k = listeClasseStandards.get(6);

		// ------- Biens immobiliers -------------//

		BienAAcheter bienachat1 = new BienAAcheter("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo1.jpg","maison", "habitation", 3, "correct", 150000);
		BienAAcheter bienachat2 = new BienAAcheter("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo2.jpg","studio", "habitation", 1, "impeccable", 140000);
		BienAAcheter bienachat3 = new BienAAcheter("indisponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo3.jpg", "studio","habitation", 1, "impeccable", 145000);

		BienAAcheter bienachat4 = new BienAAcheter("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo4.jpg","bureau", "commercial", 6, "impeccable", 250000);
		BienAAcheter bienachat5 = new BienAAcheter("indisponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo5.jpg", "bureau","commercial", 5, "à restorer", 200000);
		BienAAcheter bienachat6 = new BienAAcheter("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo6.jpg","terrain", "commercial", 15, "", 250000);

		BienALouer bienlouer1 = new BienALouer("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo7.jpg", "maison","habitation", 2, "correct", 950, 950, 100, "bail 3 ans", "non meublé");
		BienALouer bienlouer2 = new BienALouer("indisponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo8.jpg", "studio","habitation", 1, "impeccable", 500, 500, 100, "bail 1 an", " meublé");
		BienALouer bienlouer3 = new BienALouer("disponible", "01/01/2020", "0", "01/02/2020", "assets/images/photo9.jpg", "entrepot","commercial", 110, "à restorer", 1500, 1500, 300, "bail 1 an", "non meublé");
		
		// ---- Adresse pour les biens ------------------//
		Adresse adresse1b = new Adresse("rue1b", "75000", "Paris");
		Adresse adresse2b = new Adresse("rue2b", "75000", "Paris");
		Adresse adresse3b = new Adresse("rue3b", "75000", "Paris");
		Adresse adresse4b = new Adresse("rue4b", "75000", "Paris");
		Adresse adresse5b = new Adresse("rue5b", "75000", "Paris");
		Adresse adresse6b = new Adresse("rue6b", "75000", "Paris");
		Adresse adresse7b = new Adresse("rue7b", "75000", "Paris");
		Adresse adresse8b = new Adresse("rue8b", "75000", "Paris");
		Adresse adresse9b = new Adresse("rue9b", "75000", "Paris");

		//-----lien adresse avec les biens-------
		
		bienachat1.addAdresse(adresse1b);
		bienachat2.addAdresse(adresse2b);
		bienachat3.addAdresse(adresse3b);
		bienachat4.addAdresse(adresse4b);
		bienachat5.addAdresse(adresse5b);
		bienachat6.addAdresse(adresse6b);
		
		bienlouer1.addAdresse(adresse7b);
		bienlouer2.addAdresse(adresse8b);
		bienlouer3.addAdresse(adresse9b);

		
		//---- Ajout des biens et recup------------//
		
		bienAAcheterDao.save(bienachat1);
		bienAAcheterDao.save(bienachat2);
		bienAAcheterDao.save(bienachat3);
		bienAAcheterDao.save(bienachat4);
		bienAAcheterDao.save(bienachat5);
		bienAAcheterDao.save(bienachat6);
		
		bienALouer.save(bienlouer1);
		bienALouer.save(bienlouer2);
		bienALouer.save(bienlouer3);
		
		List<BienAAcheter> listeBienAchats = bienAAcheterDao.findAll();
		List<BienALouer> listeBienLouers = bienALouer.findAll();
		
		bienachat1=listeBienAchats.get(0);
		bienachat2=listeBienAchats.get(1);
		bienachat3=listeBienAchats.get(2);
		bienachat4=listeBienAchats.get(3);
		bienachat5=listeBienAchats.get(4);
		bienachat6=listeBienAchats.get(5);
		
		bienlouer1=listeBienLouers.get(0);
		bienlouer2=listeBienLouers.get(1);
		bienlouer3=listeBienLouers.get(2);
		
		
		
		//---- Lien avec proprio et classe -----//
		
		bienachat1.addClasseStandard(classeStandardVMminS3maxP200k);
		bienachat1.addProprietaire(proprietaire1);
		
		
		bienachat2.addClasseStandard(classeStandardVSminS1maxP150k);
		bienachat2.addProprietaire(proprietaire2);
		
		bienachat3.addClasseStandard(classeStandardVSminS1maxP150k);
		bienachat3.addProprietaire(proprietaire3);
		
		bienachat4.addClasseStandard(classeStandardVBminS5maxP300k);
		bienachat4.addProprietaire(proprietaire4);
		
		bienachat5.addClasseStandard(classeStandardVBminS5maxP300k);
		bienachat5.addProprietaire(proprietaire1);
		
		bienachat6.addClasseStandard(classeStandardVTminS10maxP300k);
		bienachat6.addProprietaire(proprietaire2);
		
		bienlouer1.addClasseStandard(classeStandardLMminS2maxP1k);
		bienlouer1.addProprietaire(proprietaire3);
		
		bienlouer2.addClasseStandard(classeStandardLSminS1maxP600);
		bienlouer2.addProprietaire(proprietaire4);
		
		bienlouer3.addClasseStandard(classeStandardLEminS100maxP2k);
		bienlouer3.addProprietaire(proprietaire1);
		
		//---- Modif des biens ----------//
		
		bienAAcheterDao.save(bienachat1);
		bienAAcheterDao.save(bienachat2);
		bienAAcheterDao.save(bienachat3);
		bienAAcheterDao.save(bienachat4);
		bienAAcheterDao.save(bienachat5);
		bienAAcheterDao.save(bienachat6);
		
		bienALouer.save(bienlouer1);
		bienALouer.save(bienlouer2);
		bienALouer.save(bienlouer3);
		
		listeBienAchats = bienAAcheterDao.findAll();
		listeBienLouers = bienALouer.findAll();
		
		bienachat1=listeBienAchats.get(0);
		bienachat2=listeBienAchats.get(1);
		bienachat3=listeBienAchats.get(2);
		bienachat4=listeBienAchats.get(3);
		bienachat5=listeBienAchats.get(4);
		bienachat6=listeBienAchats.get(5);
		
		bienlouer1=listeBienLouers.get(0);
		bienlouer2=listeBienLouers.get(1);
		bienlouer3=listeBienLouers.get(2);
		
		//--- Ajout des Contrats pour les biens déjà loués/achetés sans liaisons----------//

		
		Contrat contrat1 = new Contrat(145000, "01/03/2020", "numContrat1");
		Contrat contrat2 = new Contrat(200000, "15/03/2020", "numContrat2");
		Contrat contrat3 = new Contrat(500, "20/03/2020", "numContrat3");

		contratDao.save(contrat1);
		contratDao.save(contrat2);
		contratDao.save(contrat3);
		
		List<Contrat> listeContrats = contratDao.findAll();
		
		contrat1=listeContrats.get(0);
		contrat2=listeContrats.get(1);
		contrat3=listeContrats.get(2);
		
		//-- Modif des contrats avec les liaisons---//
		
		contrat1.addBienImmobiler(bienachat3);
		contrat1.addClient(client1);
		contrat1.addConseiller(conseiller1);
		contrat2.addBienImmobiler(bienachat5);
		contrat2.addClient(client2);
		contrat2.addConseiller(conseiller2);
		contrat3.addBienImmobiler(bienlouer2);
		contrat3.addClient(client3);
		contrat3.addConseiller(conseiller3);
		
		contratDao.save(contrat1);
		contratDao.save(contrat2);
		contratDao.save(contrat3);
		
		listeContrats = contratDao.findAll();
		
		contrat1=listeContrats.get(0);
		contrat2=listeContrats.get(1);
		contrat3=listeContrats.get(2);
		
		
		//------- Visites -------------//
		
		Visite visite1 = new Visite("01/04/2020", "15h");
		Visite visite2 = new Visite("02/04/2020", "15h");
		Visite visite3 = new Visite("03/04/2020", "15h");
		Visite visite4 = new Visite("04/04/2020", "15h");
		Visite visite5 = new Visite("05/04/2020", "15h");
		Visite visite6 = new Visite("06/04/2020", "15h");
		Visite visite7 = new Visite("07/04/2020", "15h");
		Visite visite8 = new Visite("08/04/2020", "15h");
		
		visiteDao.save(visite1);
		visiteDao.save(visite2);
		visiteDao.save(visite3);
		visiteDao.save(visite4);
		visiteDao.save(visite5);
		visiteDao.save(visite6);
		visiteDao.save(visite7);
		visiteDao.save(visite8);

		List<Visite> listeVisites = visiteDao.findAll();
		
		visite1 = listeVisites.get(0);
		visite2 = listeVisites.get(1);
		visite3 = listeVisites.get(2);
		visite4 = listeVisites.get(3);
		visite5 = listeVisites.get(4);
		visite6 = listeVisites.get(5);
		visite7 = listeVisites.get(6);
		visite8 = listeVisites.get(7);
		
		
		visite1.addBienImmobilier(bienachat1);
		visite1.addClient(client1);
		visite1.addConseiller(conseiller1);
		
		visite2.addBienImmobilier(bienachat1);
		visite2.addClient(client1);
		visite2.addConseiller(conseiller2);
		
		visite3.addBienImmobilier(bienachat2);
		visite3.addClient(client2);
		visite3.addConseiller(conseiller3);
		
		visite4.addBienImmobilier(bienlouer1);
		visite4.addClient(client2);
		visite4.addConseiller(conseiller3);
		
		visite5.addBienImmobilier(bienachat6);
		visite5.addClient(client3);
		visite5.addConseiller(conseiller2);
		
		visite6.addBienImmobilier(bienachat4);
		visite6.addClient(client3);
		visite6.addConseiller(conseiller2);
		
		visite7.addBienImmobilier(bienlouer2);
		visite7.addClient(client4);
		visite7.addConseiller(conseiller1);
		
		visite8.addBienImmobilier(bienlouer1);
		visite8.addClient(client4);
		visite8.addConseiller(conseiller1);
		
		visiteDao.save(visite1);
		visiteDao.save(visite2);
		visiteDao.save(visite3);
		visiteDao.save(visite4);
		visiteDao.save(visite5);
		visiteDao.save(visite6);
		visiteDao.save(visite7);
		visiteDao.save(visite8);

		listeVisites = visiteDao.findAll();
		
		visite1 = listeVisites.get(0);
		visite2 = listeVisites.get(1);
		visite3 = listeVisites.get(2);
		visite4 = listeVisites.get(3);
		visite5 = listeVisites.get(4);
		visite6 = listeVisites.get(5);
		visite7 = listeVisites.get(6);
		visite8 = listeVisites.get(7);
		
		//---- Relation clients et classes standard -----------//
		
		client1.addClasseStandard(classeStandardVMminS3maxP200k);
		client2.addClasseStandard(classeStandardVSminS1maxP150k);
		client2.addClasseStandard(classeStandardLMminS2maxP1k);
		client3.addClasseStandard(classeStandardVBminS5maxP300k);
		client3.addClasseStandard(classeStandardVTminS10maxP300k);
		client4.addClasseStandard(classeStandardLSminS1maxP600);
		client4.addClasseStandard(classeStandardLMminS2maxP1k);
		
		clientDao.save(client1);
		clientDao.save(client2);
		clientDao.save(client3);
		clientDao.save(client4);
		
		listeClients = clientDao.findAll();

		client1 = listeClients.get(0);
		client2 = listeClients.get(1);
		client3 = listeClients.get(2);
		client4 = listeClients.get(3);
		
		
		
		System.out.println("\n\n... La base de données a bien été initialisée ...\n\n");
		
		System.out.println(contratDao.findByBienImmobilierIdBien(3));
	
	}// end run

}// End class
