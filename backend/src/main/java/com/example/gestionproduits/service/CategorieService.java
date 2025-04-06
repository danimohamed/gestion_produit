package com.example.gestionproduits.service;

import com.example.gestionproduits.model.Categorie;
import com.example.gestionproduits.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    public Categorie save(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public void delete(Long id) {
        categorieRepository.deleteById(id);
    }

    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }
}
