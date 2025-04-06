
package com.example.gestionproduits.service;

import com.example.gestionproduits.model.Produit;
import com.example.gestionproduits.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> findAll() {
        return produitRepository.findAll();
    }

    public Produit save(Produit produit) {
        return produitRepository.save(produit);
    }

    public void delete(Long id) {
        produitRepository.deleteById(id);
    }

    public Optional<Produit> findById(Long id) {
        return produitRepository.findById(id);
    }
}
