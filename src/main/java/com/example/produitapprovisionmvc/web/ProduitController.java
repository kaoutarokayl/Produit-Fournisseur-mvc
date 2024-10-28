package com.example.produitapprovisionmvc.web;


import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.entities.Produit;
import com.example.produitapprovisionmvc.service.FournisseurService;
import com.example.produitapprovisionmvc.service.ProduitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @Autowired
    private FournisseurService fournisseurService;

    // Afficher la liste des produits
    @GetMapping("/produits")
    public String listeProduits(Model model) {
        model.addAttribute("produits", produitService.findAllProduits());
        return "produits";
    }

    // Afficher le formulaire de création d'un nouveau produit
    @GetMapping("/produits/new")
    public String creerProduit(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("fournisseurs", fournisseurService.findAllFournisseurs());
        return "produit_form";
    }

    // Enregistrer un produit (ajouter ou modifier)

    @PostMapping("/produits/save")
    public String saveProduit( @Validated @ModelAttribute ("produit") Produit produit,
                               BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("fournisseurs", fournisseurService.findAllFournisseurs());
            return "produit_form";
        }
        // Récupérer le fournisseur associé
        Fournisseur fournisseur = fournisseurService.findFournisseurById(produit.getFournisseur().getId());
        produit.setFournisseur(fournisseur);
        produitService.saveProduit(produit);
        return "redirect:/produits";
    }

    // Afficher le formulaire de modification d'un produit
    @GetMapping("/produits/edit/{id}")
    public String editerProduit(@PathVariable Long id, Model model) {
        Produit produit = produitService.findProduitById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
        model.addAttribute("produit", produit);
        model.addAttribute("fournisseurs", fournisseurService.findAllFournisseurs());
        return "produit_form";
    }
    @PostMapping("/produits/edit/{id}")
    public String updateProduit(@PathVariable Long id, @ModelAttribute Produit produit, BindingResult result) {
        // Vérification des erreurs
        if (result.hasErrors()) {
            return "produit_form"; // Renvoie le formulaire si des erreurs sont présentes
        }




        // Vérifiez si le produit existe
        Produit existingProduit = produitService.findProduitById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));

        // Mettez à jour le produit
        existingProduit.setNom(produit.getNom());
        existingProduit.setQuantiteEnStock(produit.getQuantiteEnStock());

        // Assurez-vous que le fournisseur est bien associé
        if (produit.getFournisseur() != null) {
            existingProduit.setFournisseur(produit.getFournisseur());
        }

      //  produitService.updateProduit(existingProduit.getId(), existingProduit); // Mettez à jour le produit dans le service

        return "redirect:/produits"; // Redirige vers la liste des produits après la mise à jour
    }
    @PostMapping("/produit/modifier/{id}")
    public String modifierProduit(@Valid @ModelAttribute("produit") Produit produit,
                                  BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/produit/modifier/" + produit.getId(); // Rediriger vers le formulaire de modification
        }
        produitService.saveProduit(produit);
        return "redirect:/produits";
    }


    // Supprimer un produit
    @GetMapping("/produits/delete/{id}")
    public String supprimerProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return "redirect:/produits";
    }
}
