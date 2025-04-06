import { useEffect, useState } from "react";
import axios from "axios";

function Categories() {
  const [categories, setCategories] = useState([]);
  const [newCategory, setNewCategory] = useState({ nom: "", description: "" });

  useEffect(() => {
    fetchCategories();
  }, []);

  const fetchCategories = () => {
    axios
      .get("http://localhost:8080/api/categories")
      .then((response) => setCategories(response.data))
      .catch((error) => console.error(error));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post("http://localhost:8080/api/categories", newCategory)
      .then(() => {
        fetchCategories();
        setNewCategory({ nom: "", description: "" });
      })
      .catch((error) => console.error(error));
  };

  const deleteCategory = (id) => {
    if (
      window.confirm("Êtes-vous sûr de vouloir supprimer cette catégorie ?")
    ) {
      axios
        .delete(`http://localhost:8080/api/categories/${id}`)
        .then(() => fetchCategories())
        .catch((error) => console.error(error));
    }
  };

  return (
    <div>
      <h2>Liste des catégories</h2>
      <ul className="list-group mb-4">
        {categories.map((category) => (
          <li
            key={category.id}
            className="list-group-item d-flex justify-content-between align-items-center"
          >
            <span>
              <strong>{category.nom}</strong>: {category.description}
            </span>
            <button
              onClick={() => deleteCategory(category.id)}
              className="btn btn-danger btn-sm"
            >
              Delete
            </button>
          </li>
        ))}
      </ul>
      <h3>Ajouter une nouvelle catégorie</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">Nom</label>
          <input
            type="text"
            className="form-control"
            value={newCategory.nom}
            onChange={(e) =>
              setNewCategory({ ...newCategory, nom: e.target.value })
            }
            required
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Description</label>
          <textarea
            className="form-control"
            value={newCategory.description}
            onChange={(e) =>
              setNewCategory({ ...newCategory, description: e.target.value })
            }
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Ajouter
        </button>
      </form>
    </div>
  );
}

export default Categories;
