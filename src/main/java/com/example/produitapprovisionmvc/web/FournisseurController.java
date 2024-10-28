package com.example.produitapprovisionmvc.web;


import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.entities.Produit;
import com.example.produitapprovisionmvc.repositories.FournisseurRepository;
import com.example.produitapprovisionmvc.repositories.ProduitRepository;
import com.example.produitapprovisionmvc.service.FournisseurService;
import com.example.produitapprovisionmvc.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class FournisseurController {
    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/fournisseur/{id}")
    public String detailFournisseur(@PathVariable Long id, Model model) {
        Fournisseur fournisseur = fournisseurService.findFournisseurById(id);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseurDetail";
    }




    @PostMapping("/fournisseur")
    public String ajouterFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurService.saveFournisseur(fournisseur);
        return "redirect:/fournisseurDetail";
    }


   @PostMapping("/fournisseur/update/{id}")
    public String modifierFournisseur(@PathVariable Long id, @ModelAttribute Fournisseur fournisseurDetails) {
        fournisseurService.updateFournisseur(id, fournisseurDetails);
        return "redirect:/fournisseurDetail";
    }

    @GetMapping("/fournisseur/delete/{id}")
    public String supprimerFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return "redirect:/fournisseurDetail";
    }
}