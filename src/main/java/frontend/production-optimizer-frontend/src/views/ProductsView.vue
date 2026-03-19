<template>
  <div class="container">
    <h1>Products</h1>

    <div class="toolbar">
      <input v-model="search" placeholder="Search product..." />
      <button @click="openCreate">+ New</button>
    </div>

    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Price</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="p in filteredProducts" :key="p.id">
          <td>{{ p.name }}</td>
          <td>{{ p.price }}</td>
          <td>
            <div class="actions">
              <button class="edit-btn" @click="editProduct(p)">Edit</button>
              <button class="delete-btn" @click="deleteProduct(p.id)">
                Delete
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- MODAL CREATE / EDIT -->
    <Modal :show="showModal" @close="showModal = false">
      <h3>{{ form.id ? "Edit Product" : "New Product" }}</h3>

      <div class="form-group">
        <label>Name</label>
        <input v-model="form.name" />
      </div>

      <div class="form-group">
        <label>Price</label>
        <input v-model="form.price" type="number" />
      </div>

      <div class="form-actions">
        <button @click="saveProduct">Save</button>
        <button class="cancel-btn" @click="showModal = false">Cancel</button>
      </div>
    </Modal>

    <!-- MODAL DELETE -->
    <Modal :show="showDeleteModal" @close="showDeleteModal = false">
      <h3>Confirm Delete</h3>

      <p>Are you sure you want to delete this product?</p>

      <div style="display: flex; gap: 10px; margin-top: 10px">
        <button @click="confirmDelete">Yes, Delete</button>
        <button @click="showDeleteModal = false">Cancel</button>
      </div>
    </Modal>
  </div>
</template>

<script>
import api from "../services/api";
import Modal from "../components/Modal.vue";

export default {
  components: {
    Modal,
  },

  data() {
    return {
      products: [],
      search: "",
      showModal: false,
      showDeleteModal: false,
      deleteId: null,
      form: {
        id: null,
        name: "",
        price: 0,
      },
    };
  },

  computed: {
    filteredProducts() {
      return this.products
        .filter((p) => p.name.toLowerCase().includes(this.search.toLowerCase()))
        .sort((a, b) => a.id - b.id);
    },
  },

  methods: {
    async loadProducts() {
      const res = await api.get("/products");
      this.products = res.data;
    },

    openCreate() {
      this.form = { id: null, name: "", price: 0 };
      this.showModal = true;
    },

    editProduct(p) {
      this.form = { ...p };
      this.showModal = true;
    },

    async saveProduct() {
      if (this.form.id) {
        await api.put(`/products/${this.form.id}`, this.form);
      } else {
        await api.post("/products", this.form);
      }

      this.showModal = false;
      this.loadProducts();
    },

    deleteProduct(id) {
      this.deleteId = id;
      this.showDeleteModal = true;
    },

    async confirmDelete() {
      await api.delete(`/products/${this.deleteId}`);
      this.showDeleteModal = false;
      this.loadProducts();
    },
  },

  mounted() {
    this.loadProducts();
  },
};
</script>

<style>
.actions {
  display: flex;
  gap: 10px;
}

.container {
  max-width: 1000px;
  margin: auto;
}

h1 {
  margin-bottom: 20px;
}

.toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  gap: 10px;
}

input {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  flex: 1;
  margin-bottom: 10px;
}

button {
  padding: 10px 14px;
  border: none;
  border-radius: 6px;
  background: #2563eb;
  color: white;
  cursor: pointer;
}

button:hover {
  background: #1d4ed8;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

th {
  background: #f3f4f6;
  text-align: left;
  padding: 12px;
}

td {
  padding: 12px;
  border-top: 1px solid #eee;
}

tr:hover {
  background: #f9fafb;
}

.edit-btn {
  background: #f59e0b;
}

.edit-btn:hover {
  background: #d97706;
}

.delete-btn {
  background: #dc2626;
}

.delete-btn:hover {
  background: #b91c1c;
}

.form-group{
  display:flex;
  flex-direction:column;
  margin-bottom:12px;
}

label{
  font-size:14px;
  margin-bottom:4px;
  color:#555;
}

.form-actions{
  display:flex;
  justify-content:flex-end;
  gap:10px;
  margin-top:10px;
}

.cancel-btn{
  background:#6b7280;
}

.cancel-btn:hover{
  background:#4b5563;
}
</style>
