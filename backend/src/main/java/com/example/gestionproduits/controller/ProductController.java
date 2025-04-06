package com.example.gestionproduits.controller;

import com.example.gestionproduits.model.Product;
import com.example.gestionproduits.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}
