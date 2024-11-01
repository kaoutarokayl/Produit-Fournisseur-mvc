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




    // Supprimer un produit par ID
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }



}
