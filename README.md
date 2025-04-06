# Gestion des Produits

Ce projet est une application complète pour la gestion des produits et des catégories, développée avec **Spring Boot** pour le backend et **React** pour le frontend.

![Spring Boot Logo](https://upload.wikimedia.org/wikipedia/commons/4/44/Spring_Framework_Logo_2018.svg)
![React Logo](https://upload.wikimedia.org/wikipedia/commons/a/a7/React-icon.svg)

## Structure du Projet

- **Frontend** : Développé avec React, utilisant React Router pour la navigation et Axios pour les appels API.
- **Backend** : Développé avec Spring Boot, utilisant Spring Data JPA pour la gestion des données et H2 comme base de données en mémoire.
- **Modèles** : `Produit`, `Categorie`
- **Dépôts** : `ProduitRepository`, `CategorieRepository`
- **Services** : `ProduitService`, `CategorieService`
- **Contrôleurs** : `ProduitController`, `CategorieController`

## Fonctionnalités

- Ajouter, modifier et supprimer des produits.
- Ajouter, modifier et supprimer des catégories.
- Afficher la liste des produits et des catégories.
- Gestion des relations entre produits et catégories.
- Téléchargement et affichage d'images pour les produits.

## Configuration

La base de données H2 est configurée dans `application.properties`. Vous pouvez accéder à la console H2 via [http://localhost:8080/h2-console](http://localhost:8080/h2-console).

## Instructions de Test

Utilisez `curl`, Postman ou l'interface utilisateur React pour tester les fonctionnalités.

### Exemple de requêtes API

```bash
# Créer une catégorie
curl -X POST -H "Content-Type: application/json" -d '{"nom":"Électronique", "description":"Appareils"}' http://localhost:8080/api/categories

# Supprimer une catégorie
curl -X DELETE http://localhost:8080/api/categories/1

# Créer un produit lié à une catégorie
curl -X POST -H "Content-Type: application/json" -d '{"nom":"Ordinateur", "prix":1500.50, "categorie": {"id":1}}' http://localhost:8080/api/produits

# Supprimer un produit
curl -X DELETE http://localhost:8080/api/produits/1
```

## Démarrage du Projet

### Backend

1. Assurez-vous d'avoir Java 17 installé.
2. Naviguez dans le dossier `backend` et exécutez la commande suivante :
   ```bash
   mvn spring-boot:run
   ```
3. Le backend sera accessible sur [http://localhost:8080](http://localhost:8080).

### Frontend

1. Assurez-vous d'avoir Node.js installé.
2. Naviguez dans le dossier `frontend` et exécutez les commandes suivantes :
   ```bash
   npm install
   npm start
   ```
3. Le frontend sera accessible sur [http://localhost:3000](http://localhost:3000).

## Technologies Utilisées

### Backend
- **Spring Boot** : Framework Java pour le développement rapide d'applications.
- **Spring Data JPA** : Gestion des données avec Hibernate.
- **H2 Database** : Base de données en mémoire pour le développement et les tests.

### Frontend
- **React** : Bibliothèque JavaScript pour la création d'interfaces utilisateur.
- **Bootstrap** : Framework CSS pour un design réactif.
- **Axios** : Librairie pour les appels HTTP.

## Aperçu

### Liste des Produits
![Liste des Produits](https://via.placeholder.com/800x400?text=Liste+des+Produits)

### Ajouter un Produit
![Ajouter un Produit](https://via.placeholder.com/800x400?text=Ajouter+un+Produit)

## Auteur

Mohamed Dani
