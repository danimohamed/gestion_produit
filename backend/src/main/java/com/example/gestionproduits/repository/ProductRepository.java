package com.example.gestionproduits.repository;

import com.example.gestionproduits.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
