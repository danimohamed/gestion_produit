package com.example.gestionproduits.controller;

import com.example.gestionproduits.model.Produit;
import com.example.gestionproduits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.example.gestionproduits.model.Categorie;
import com.example.gestionproduits.repository.CategorieRepository;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React app
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping
    public List<Produit> getAll() {
        return produitService.findAll();
    }

    @PostMapping(consumes = "multipart/form-data")
    public Produit create(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prix") double prix,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("categorie_id") Long categorieId) {
        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setDescription(description);
        produit.setPrix(prix);

        // Associer la catégorie
        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée"));
        produit.setCategorie(categorie);

        // Gérer l'image
        if (image != null && !image.isEmpty()) {
            produit.setImage(image.getOriginalFilename()); // Enregistrer le nom du fichier
        }

        return produitService.save(produit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getById(@PathVariable Long id) {
        return produitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Produit> update(
            @PathVariable Long id,
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prix") double prix,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("categorie_id") Long categorieId) {
        return produitService.findById(id).map(produit -> {
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setPrix(prix);

            // Associer la catégorie
            Categorie categorie = categorieRepository.findById(categorieId)
                    .orElseThrow(() -> new IllegalArgumentException("Catégorie non trouvée"));
            produit.setCategorie(categorie);

            // Gérer l'image
            if (image != null && !image.isEmpty()) {
                produit.setImage(image.getOriginalFilename()); // Enregistrer le nom du fichier
            }

            return ResponseEntity.ok(produitService.save(produit));
        }).orElse(ResponseEntity.notFound().build());
    }
}
