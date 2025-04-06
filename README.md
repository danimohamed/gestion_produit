# Gestion des Produits

Ce projet est une application Spring Boot pour la gestion des produits et des catégories. Elle utilise une base de données H2 en mémoire.

## Structure du Projet

- **Modèles** : `Produit`, `Categorie`
- **Dépôts** : `ProduitRepository`, `CategorieRepository`
- **Services** : `ProduitService`, `CategorieService`
- **Contrôleurs** : `ProduitController`, `CategorieController`

## Configuration

La base de données H2 est configurée dans `application.properties`.

## Instructions de Test

Utilisez `curl` ou Postman pour tester les API REST :

### Exemple de requêtes

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
