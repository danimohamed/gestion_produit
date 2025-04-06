import { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function EditProduct() {
  const { id } = useParams();
  const [categories, setCategories] = useState([]);
  const [product, setProduct] = useState({ nom: '', description: '', prix: '', image: null, categorie_id: '' });
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/produits/${id}`)
      .then(response => setProduct(response.data))
      .catch(error => console.error(error));

    axios.get('http://localhost:8080/api/categories')
      .then(response => setCategories(response.data))
      .catch(error => console.error(error));
  }, [id]);

  const handleSubmit = (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('nom', product.nom);
    formData.append('description', product.description);
    formData.append('prix', product.prix);
    if (product.image instanceof File) {
      formData.append('image', product.image);
    }
    formData.append('categorie_id', product.categorie_id);

    axios.put(`http://localhost:8080/api/produits/${id}`, formData, {
      headers: { 'Content-Type': 'multipart/form-data' }
    })
      .then(() => navigate('/products'))
      .catch(error => console.error(error));
  };

  return (
    <div>
      <h2>Modifier le produit</h2>
      <form onSubmit={handleSubmit}>
        {/* ...existing fields... */}
        <div className="mb-3">
          <label className="form-label">Image</label>
          <input
            type="file"
            className="form-control"
            onChange={(e) => setProduct({ ...product, image: e.target.files[0] })}
          />
        </div>
        {/* ...existing fields... */}
        <button type="submit" className="btn btn-primary">Modifier</button>
      </form>
    </div>
  );
}

export default EditProduct;
