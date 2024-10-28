package com.example.produitapprovisionmvc.service;

import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.repositories.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;

    // Récupérer tous les fournisseurs
    public List<Fournisseur> findAllFournisseurs() {
        return fournisseurRepository.findAll();
    }

    // Ajouter un nouveau fournisseur
    public Fournisseur saveFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    // Trouver un fournisseur par ID
    public Fournisseur findFournisseurById(Long id) {
        return fournisseurRepository.findById(id).orElse(null);
    }


    // Mettre à jour un fournisseur
    public Fournisseur updateFournisseur(Long id, Fournisseur fournisseurDetails) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec ID : " + id));
        fournisseur.setNom(fournisseurDetails.getNom());
        fournisseur.setContact(fournisseurDetails.getContact());
        return fournisseurRepository.save(fournisseur);
    }

    // Supprimer un fournisseur par ID
    public void deleteFournisseur(Long id) {
        fournisseurRepository.deleteById(id);
    }
}
