<template>
  <div class="container">
    <h1>Raw Materials</h1>

    <div class="toolbar">
      <input v-model="search" placeholder="Search material..." />
      <button @click="openCreate">+ New</button>
    </div>

    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Cost</th>
          <th>Unit</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="rm in filteredMaterials" :key="rm.id">
          <td>{{ rm.name }}</td>
          <td>{{ rm.stockQuantity }}</td>
          <td>{{ rm.unit?.name }}</td>
          <td>
            <div class="actions">
              <button class="edit-btn" @click="editMaterial(rm)">Edit</button>
              <button class="delete-btn" @click="deleteMaterial(rm.id)">
                Delete
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- CREATE / EDIT -->
    <Modal :show="showModal" @close="showModal = false">
      <h3>{{ form.id ? "Edit Material" : "New Material" }}</h3>

      <div class="form-group">
        <label>Name</label>
        <input v-model="form.name" />
      </div>

      <div class="form-group">
        <label>Stock Quantity</label>
        <input v-model="form.stockQuantity" type="number" />
      </div>

      <div class="form-group">
        <label>Unit</label>
        <select v-model="form.unitId">
          <option v-for="u in units" :key="u.id" :value="u.id">
            {{ u.name }}
          </option>
        </select>
      </div>

      <div class="form-actions">
        <button @click="saveMaterial">Save</button>
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
      materials: [],
      units: [],
      search: "",
      showModal: false,
      showDeleteModal: false,
      deleteId: null,
      form: {
        id: null,
        name: "",
        stockQuantity: 0,
        unitId: null,
      },
    };
  },

  computed: {
    filteredMaterials() {
      return this.materials
        .filter((m) => m.name.toLowerCase().includes(this.search.toLowerCase()))
        .sort((a, b) => a.id - b.id);
    },
  },

  methods: {
    async loadMaterials() {
      const res = await api.get("/raw-materials");
      this.materials = res.data;
    },

    async loadUnits() {
      const res = await api.get("/units");
      this.units = res.data;
    },

    openCreate() {
      this.form = { id: null, name: "", cost: 0, unitId: null };
      this.showModal = true;
    },

    editMaterial(m) {
      this.form = {
        id: m.id,
        name: m.name,
        stockQuantity: m.stockQuantity,
        unitId: m.unit?.id,
      };
      this.showModal = true
    },

    async saveMaterial() {
      const payload = {
        name: this.form.name,
        stockQuantity: this.form.stockQuantity,
        unit: { id: this.form.unitId },
      };

      if (this.form.id) {
        await api.put(`/raw-materials/${this.form.id}`, payload);
      } else {
        await api.post("/raw-materials", payload);
      }

      this.showModal = false;
      this.loadMaterials();
    },

    deleteMaterial(id) {
      this.deleteId = id;
      this.showDeleteModal = true;
    },

    async confirmDelete() {
      await api.delete(`/raw-materials/${this.deleteId}`);
      this.showDeleteModal = false;
      this.loadMaterials();
    },
  },

  mounted() {
    this.loadMaterials();
    this.loadUnits();
  },
};
</script>

<style scoped>
.container {
  max-width: 1000px;
  margin: auto;
}

.toolbar {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  gap: 10px;
}

input,
select {
  padding: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  width: 100%;
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

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.cancel-btn {
  background: #6b7280;
}
</style>
