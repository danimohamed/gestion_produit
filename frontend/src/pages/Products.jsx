import { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function Products() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/produits')
      .then(response => setProducts(response.data))
      .catch(error => console.error(error));
  }, []);

  const deleteProduct = (id) => {
    axios.delete(`http://localhost:8080/api/produits/${id}`)
      .then(() => setProducts(products.filter(product => product.id !== id)))
      .catch(error => console.error(error));
  };

  return (
    <div>
      <h2>Liste des produits</h2>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Nom</th>
            <th>Description</th>
            <th>Prix</th>
            <th>Image</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>{product.nom}</td>
              <td>{product.description}</td>
              <td>{product.prix} €</td>
              <td><img src={product.image} alt={product.nom} style={{ width: '50px' }} /></td>
              <td>
                <Link to={`/edit-product/${product.id}`} className="btn btn-primary btn-sm me-2">Edit</Link>
                <button onClick={() => deleteProduct(product.id)} className="btn btn-danger btn-sm">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Products;
