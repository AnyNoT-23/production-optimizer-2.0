<template>
  <div class="container">
    <h1>Product Composition</h1>

    <div class="toolbar">
      <input v-model="search" placeholder="Search product or material..." />
      <button @click="openCreate">+ New</button>
    </div>

    <table>
      <thead>
        <tr>
          <th>Product</th>
          <th>Raw Material</th>
          <th>Quantity</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="pc in filteredCompositions" :key="pc.id">
          <td>{{ pc.product?.name }}</td>
          <td>{{ pc.rawMaterial?.name }}</td>
          <td>{{ pc.requiredQuantity }}</td>
          <td>
            <div class="actions">
              <button class="edit-btn" @click="edit(pc)">Edit</button>
              <button class="delete-btn" @click="remove(pc.id)">Delete</button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- CREATE / EDIT -->
    <Modal :show="showModal" @close="showModal = false">
      <h3>{{ form.id ? "Edit Composition" : "New Composition" }}</h3>

      <div class="form-group">
        <label>Product</label>
        <select v-model="form.productId">
          <option v-for="p in products" :key="p.id" :value="p.id">
            {{ p.name }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label>Raw Material</label>
        <select v-model="form.rawMaterialId">
          <option v-for="rm in materials" :key="rm.id" :value="rm.id">
            {{ rm.name }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label>Required Quantity</label>
        <input v-model="form.requiredQuantity" type="number" />
      </div>

      <div class="form-actions">
        <button @click="save">Save</button>
        <button class="cancel-btn" @click="showModal = false">Cancel</button>
      </div>
    </Modal>

    <!-- DELETE -->
    <Modal :show="showDeleteModal" @close="showDeleteModal = false">
      <h3>Confirm Delete</h3>
      <p>Are you sure?</p>

      <div class="form-actions">
        <button @click="confirmDelete">Yes, Delete</button>
        <button class="cancel-btn" @click="showDeleteModal = false">
          Cancel
        </button>
      </div>
    </Modal>
  </div>
</template>

<script>
import api from "../services/api";
import Modal from "../components/Modal.vue";

export default {
  components: { Modal },

  data() {
    return {
      search: "",
      compositions: [],
      products: [],
      materials: [],
      showModal: false,
      showDeleteModal: false,
      deleteId: null,
      form: {
        id: null,
        productId: null,
        rawMaterialId: null,
        requiredQuantity: 0,
      },
    };
  },

  methods: {
    async loadAll() {
      const [pc, p, rm] = await Promise.all([
        api.get("/product-compositions"),
        api.get("/products"),
        api.get("/raw-materials"),
      ]);

      this.compositions = pc.data;
      this.products = p.data;
      this.materials = rm.data;
    },

    openCreate() {
      this.form = {
        id: null,
        productId: null,
        rawMaterialId: null,
        requiredQuantity: 0,
      };
      this.showModal = true;
    },

    edit(pc) {
      this.form = {
        id: pc.id,
        productId: pc.product?.id,
        rawMaterialId: pc.rawMaterial?.id,
        requiredQuantity: pc.requiredQuantity,
      };
      this.showModal = true;
    },

    async save() {
      const payload = {
        product: { id: this.form.productId },
        rawMaterial: { id: this.form.rawMaterialId },
        requiredQuantity: this.form.requiredQuantity,
      };

      if (this.form.id) {
        await api.put(`/product-compositions/${this.form.id}`, payload);
      } else {
        await api.post("/product-compositions", payload);
      }

      this.showModal = false;
      this.loadAll();
    },

    remove(id) {
      this.deleteId = id;
      this.showDeleteModal = true;
    },

    async confirmDelete() {
      await api.delete(`/product-compositions/${this.deleteId}`);
      this.showDeleteModal = false;
      this.loadAll();
    },
  },

  mounted() {
    this.loadAll();
  },

  computed: {
    filteredCompositions() {
      return this.compositions.filter((pc) => {
        const product = pc.product?.name?.toLowerCase() || "";
        const material = pc.rawMaterial?.name?.toLowerCase() || "";
        const search = this.search.toLowerCase();

        return product.includes(search) || material.includes(search);
      });
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 1000px;
  margin: auto;
}

.toolbar{
  display:flex;
  justify-content:space-between;
  gap:10px;
  margin-bottom:20px;
}

input{
  flex:1;
  padding:10px;
  border-radius:6px;
  border:1px solid #ccc;
}

.actions {
  display: flex;
  gap: 10px;
}

button {
  padding: 10px;
  border: none;
  border-radius: 6px;
  background: #2563eb;
  color: white;
}

.edit-btn {
  background: #f59e0b;
}

.delete-btn {
  background: #dc2626;
}

.form-group {
  margin-bottom: 12px;
}

select,
input {
  width: 100%;
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.cancel-btn {
  background: #6b7280;
}
</style>
