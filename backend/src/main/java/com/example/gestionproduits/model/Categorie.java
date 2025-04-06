
package com.example.gestionproduits.model;

import jakarta.persistence.*;

@Entity
public class Categorie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;

    // Getters and Setters
}
