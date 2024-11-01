package com.example.produitapprovisionmvc;

import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.entities.Produit;
import com.example.produitapprovisionmvc.repositories.FournisseurRepository;
import com.example.produitapprovisionmvc.repositories.ProduitRepository;
import com.example.produitapprovisionmvc.service.ProduitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProduitapprovisionmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProduitapprovisionmvcApplication.class, args);

	}
	@Bean
	CommandLineRunner commandeLineRunner (ProduitRepository produitRepository, FournisseurRepository fournisseurRepository){
		return args -> {
// Créer quelques fournisseurs
			Fournisseur fournisseur1 = new Fournisseur(1L,"Fournisseur A", 067562);
			Fournisseur fournisseur2 = new Fournisseur(2L,"Fournisseur B", 063722);
			Fournisseur fournisseur3 = new Fournisseur(3L,"Fournisseur C", 063556);
			Fournisseur fournisseur4 = new Fournisseur(4L,"Fournisseur D", 063732);
			// Enregistrer les fournisseurs dans la base de données
			fournisseurRepository.save(fournisseur1);
			fournisseurRepository.save(fournisseur2);
			fournisseurRepository.save(fournisseur3);
			fournisseurRepository.save(fournisseur4);


			/*// Créer des produits et les associer aux fournisseurs
			Produit produit1 = new Produit(1L,"Produit 1", 100, fournisseur1);
			Produit produit2 = new Produit(2L,"Produit 2", 50, fournisseur1);
			Produit produit3 = new Produit(3L,"Produit 3", 200, fournisseur2);

			// Enregistrer les produits dans la base de données
			produitRepository.save(produit1);
			produitRepository.save(produit2);
			produitRepository.save(produit3);

*/
			System.out.println("Données initialisées avec succès !");
		};
	}

}
