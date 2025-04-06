package com.example.gestionproduits.controller;

import com.example.gestionproduits.model.Produit;
import com.example.gestionproduits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import com.example.gestionproduits.model.Categorie; // Ensure this import is present

@RestController
@RequestMapping("/api/produits")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React app
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAll() {
        return produitService.findAll();
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> create(
            @RequestParam("nom") String nom,
            @RequestParam("description") String description,
            @RequestParam("prix") double prix,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("categorie_id") Long categorieId) {
        try {
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setDescription(description);
            produit.setPrix(prix);
            if (image != null && !image.isEmpty()) {
                produit.setImage(image.getOriginalFilename()); // Save the filename
            }
            Categorie categorie = new Categorie(); // Correctly instantiate Categorie
            categorie.setId(categorieId);
            produit.setCategorie(categorie);

            Produit savedProduit = produitService.save(produit);
            return ResponseEntity.ok(savedProduit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du produit: " + e.getMessage());
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createJson(
            @RequestBody Produit produit) {
        try {
            Produit savedProduit = produitService.save(produit);
            return ResponseEntity.ok(savedProduit);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du produit: " + e.getMessage());
        }
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
            if (image != null && !image.isEmpty()) {
                produit.setImage(image.getOriginalFilename()); // Save the filename
            }
            Categorie categorie = new Categorie(); // Correctly instantiate Categorie
            categorie.setId(categorieId);
            produit.setCategorie(categorie);

            return ResponseEntity.ok(produitService.save(produit));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Produit> updateJson(
            @PathVariable Long id,
            @RequestBody Produit produit) {
        return produitService.findById(id).map(existingProduit -> {
            existingProduit.setNom(produit.getNom());
            existingProduit.setDescription(produit.getDescription());
            existingProduit.setPrix(produit.getPrix());
            existingProduit.setImage(produit.getImage());
            existingProduit.setCategorie(produit.getCategorie());

            return ResponseEntity.ok(produitService.save(existingProduit));
        }).orElse(ResponseEntity.notFound().build());
    }
}
