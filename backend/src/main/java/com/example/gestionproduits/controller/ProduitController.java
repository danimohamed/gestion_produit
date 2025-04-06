package com.example.gestionproduits.controller;

import com.example.gestionproduits.model.Produit;
import com.example.gestionproduits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

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

    @PostMapping
    public Produit create(@RequestBody Produit produit) {
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

    @PutMapping("/{id}")
    public ResponseEntity<Produit> update(@PathVariable Long id, @RequestBody Produit updatedProduit) {
        return produitService.findById(id).map(produit -> {
            produit.setNom(updatedProduit.getNom());
            produit.setDescription(updatedProduit.getDescription());
            produit.setPrix(updatedProduit.getPrix());
            produit.setImage(updatedProduit.getImage());
            produit.setCategorie(updatedProduit.getCategorie());
            return ResponseEntity.ok(produitService.save(produit));
        }).orElse(ResponseEntity.notFound().build());
    }
}
