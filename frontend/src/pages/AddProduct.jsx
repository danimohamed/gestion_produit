import { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddProduct() {
  const [categories, setCategories] = useState([]);
  const [product, setProduct] = useState({ nom: '', description: '', prix: '', image: null, categorie_id: '' });
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error(error));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('nom', product.nom);
    formData.append('description', product.description);
    formData.append('prix', product.prix);
    formData.append('image', product.image);
    formData.append('categorie_id', product.categorie_id);

    axios.post('http://localhost:8080/api/produits', formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
      .then(() => navigate('/products'))
      .catch(error => console.error(error));
  };

  return (
    <div>
      <h2>Ajouter un produit</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nom</label>
          <input type="text" className="form-control" value={product.nom} onChange={(e) => setProduct({ ...product, nom: e.target.value })} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Description</label>
          <textarea className="form-control" value={product.description} onChange={(e) => setProduct({ ...product, description: e.target.value })} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Prix</label>
          <input type="number" className="form-control" value={product.prix} onChange={(e) => setProduct({ ...product, prix: e.target.value })} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Image</label>
          <input
            type="file"
            className="form-control"
            onChange={(e) => setProduct({ ...product, image: e.target.files[0] })}
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Catégorie</label>
          <select className="form-control" value={product.categorie_id} onChange={(e) => setProduct({ ...product, categorie_id: e.target.value })} required>
            <option value="">Sélectionnez une catégorie</option>
            {categories.map(category => (
              <option key={category.id} value={category.id}>{category.nom}</option>
            ))}
          </select>
        </div>
        <button type="submit" className="btn btn-success">Ajouter</button>
      </form>
    </div>
  );
}

export default AddProduct;
