<template>
  <div class="container">

    <h1>Units</h1>

    <div class="toolbar">
      <input v-model="search" placeholder="Search unit..." />
      <button @click="openCreate">+ New</button>
    </div>

    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Symbol</th>
          <th>Actions</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="u in filteredUnits" :key="u.id">
          <td>{{ u.name }}</td>
          <td>{{ u.symbol }}</td>
          <td>
            <div class="actions">
              <button class="edit-btn" @click="editUnit(u)">Edit</button>
              <button class="delete-btn" @click="deleteUnit(u.id)">Delete</button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>

    <!-- MODAL CREATE / EDIT -->
    <Modal :show="showModal" @close="showModal = false">

      <h3>{{ form.id ? "Edit Unit" : "New Unit" }}</h3>

      <div class="form-group">
        <label>Name</label>
        <input v-model="form.name" />
      </div>

      <div class="form-group">
        <label>Symbol</label>
        <input v-model="form.symbol" />
      </div>

      <div class="form-actions">
        <button @click="saveUnit">Save</button>
        <button class="cancel-btn" @click="showModal = false">Cancel</button>
      </div>

    </Modal>

    <!-- DELETE MODAL -->
    <Modal :show="showDeleteModal" @close="showDeleteModal = false">
      <h3>Confirm Delete</h3>
      <p>Are you sure?</p>

      <div class="form-actions">
        <button @click="confirmDelete">Yes, Delete</button>
        <button class="cancel-btn" @click="showDeleteModal = false">Cancel</button>
      </div>
    </Modal>

  </div>
</template>

<script>
import api from "../services/api"
import Modal from "../components/Modal.vue"

export default {
  components: { Modal },

  data() {
    return {
      units: [],
      search: "",
      showModal: false,
      showDeleteModal: false,
      deleteId: null,
      form: {
        id: null,
        name: "",
        symbol: ""
      }
    }
  },

  computed: {
    filteredUnits() {
      return this.units
        .filter(u =>
          u.name.toLowerCase().includes(this.search.toLowerCase())
        )
        .sort((a, b) => a.id - b.id)
    }
  },

  methods: {
    async loadUnits() {
      const res = await api.get("/units")
      this.units = res.data
    },

    openCreate() {
      this.form = { id: null, name: "", symbol: "" }
      this.showModal = true
    },

    editUnit(u) {
      this.form = { ...u }
      this.showModal = true
    },

    async saveUnit() {
      if (this.form.id) {
        await api.put(`/units/${this.form.id}`, this.form)
      } else {
        await api.post("/units", this.form)
      }

      this.showModal = false
      this.loadUnits()
    },

    deleteUnit(id) {
      this.deleteId = id
      this.showDeleteModal = true
    },

    async confirmDelete() {
      await api.delete(`/units/${this.deleteId}`)
      this.showDeleteModal = false
      this.loadUnits()
    }
  },

  mounted() {
    this.loadUnits()
  }
}
</script>

<style scoped>
/* reutiliza o mesmo estilo do products */
.container{
  max-width:1000px;
  margin:auto;
}

.toolbar{
  display:flex;
  justify-content:space-between;
  margin-bottom:20px;
  gap:10px;
}

input{
  padding:10px;
  border-radius:6px;
  border:1px solid #ccc;
  flex:1;
}

.actions{
  display:flex;
  gap:10px;
}

button{
  padding:10px;
  border:none;
  border-radius:6px;
  background:#2563eb;
  color:white;
}

.edit-btn{
  background:#f59e0b;
}

.delete-btn{
  background:#dc2626;
}
</style>