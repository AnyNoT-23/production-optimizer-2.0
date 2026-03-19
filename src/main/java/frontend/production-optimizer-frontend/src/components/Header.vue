<script>
export default {
  data() {
    return {
      user: localStorage.getItem("user"),
      open: false 
    };
  },
  methods: {
    toggleMenu() {
      this.open = !this.open; 
    },
    logout() {
      localStorage.removeItem("auth");
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
  },
};
</script>

<template>
  <header class="header">
    <div class="logo">Production Optimizer</div>

    <button class="menu-btn" @click="toggleMenu">☰</button>

    <nav v-if="open" class="menu">
      <router-link to="/products" @click="open = false">Products</router-link>
      <router-link to="/raw-materials" @click="open = false">Raw Materials</router-link>
      <router-link to="/units" @click="open = false">Units</router-link>
      <router-link to="/product-compositions" @click="open = false">Compositions</router-link>
      <router-link to="/production" @click="open = false">Production</router-link>
      <router-link to="/" @click="open = false">Dashboard</router-link>
      <span class="user">👤 {{ user }}</span>
      <button class="logout" @click="logout">Logout</button>
    </nav>
  </header>
</template>

<style>
.user {
  margin-left: auto;
  margin-right: 10px;
  font-weight: bold;
}

.logout {
  background: #ef4444;
  color: white;
  border: none;
  padding: 6px 10px;
  border-radius: 6px;
  cursor: pointer;
}

.logout:hover {
  background: #dc2626;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  background: #1f2937;
  color: white;
}

.logo {
  font-weight: bold;
  font-size: 18px;
}

.menu-btn {
  background: none;
  border: none;
  color: white;
  font-size: 20px;
  cursor: pointer;
}

.menu {
  position: absolute;
  top: 60px;
  right: 10px;
  background: white;
  color: black;
  display: flex;
  flex-direction: column;
  padding: 10px;
  border-radius: 6px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}

.menu a {
  padding: 8px;
  text-decoration: none;
  color: black;
}
</style>
