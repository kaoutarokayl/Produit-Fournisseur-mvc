package com.example.produitapprovisionmvc.service;

import com.example.produitapprovisionmvc.entities.Produit;
import com.example.produitapprovisionmvc.repositories.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    // Récupérer tous les produits
    public List<Produit> findAllProduits() {
        return produitRepository.findAll();
    }

    // Ajouter un nouveau produit
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Trouver un produit par ID
    public Optional<Produit> findProduitById(Long id) {
        return produitRepository.findById(id);
    }

    // Mettre à jour un produit
   /* public Produit updateProduit(Produit produitDetails) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec ID : " + id));

        if (produitDetails.getNom() != null) {
            produit.setNom(produitDetails.getNom());
        }
        if (produitDetails.getQuantiteEnStock() >= 0) { // Vérifiez que la quantité est valide
            produit.setQuantiteEnStock(produitDetails.getQuantiteEnStock());
        }
        if (produitDetails.getFournisseur() != null) {
            // Vous pouvez ajouter une vérification pour s'assurer que le fournisseur existe
            produit.setFournisseur(produitDetails.getFournisseur());
        }

        return produitRepository.save(produit);
    }

*/
    // Supprimer un produit par ID
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }



}
