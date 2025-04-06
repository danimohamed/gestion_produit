
package com.example.gestionproduits.repository;

import com.example.gestionproduits.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {}
