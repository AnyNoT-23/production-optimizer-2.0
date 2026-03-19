<template>
  <div class="login-container">

    <div class="login-box">
      <h1 class="title">Production Optimizer</h1>
      <p class="subtitle">Sign in to continue</p>

      <input v-model="username" placeholder="Username" />
      <input v-model="password" type="password" placeholder="Password" />

      <button @click="login">Login</button>

      <p v-if="error" class="error">Invalid username or password</p>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      password: "",
      error: false
    }
  },

  methods: {
    login() {
      if (this.username === "Antony93" && this.password === "202418") {
        localStorage.setItem("auth", "true")
        localStorage.setItem("user", this.username)

        this.$router.push("/")
      } else {
        this.error = true
      }
    }
  }
}
</script>

<style scoped>
.login-container{
  height:100vh;
  display:flex;
  justify-content:center;
  align-items:center;
  background:linear-gradient(135deg,#2563eb,#1e3a8a);
}

.login-box{
  background:white;
  padding:40px;
  border-radius:16px;
  width:320px;
  display:flex;
  flex-direction:column;
  gap:15px;
  box-shadow:0 10px 30px rgba(0,0,0,0.2);
}

.title{
  text-align:center;
  margin:0;
}

.subtitle{
  text-align:center;
  color:#6b7280;
  margin-bottom:10px;
}

input{
  padding:12px;
  border-radius:8px;
  border:1px solid #ddd;
}

button{
  padding:12px;
  background:#2563eb;
  color:white;
  border:none;
  border-radius:8px;
  font-weight:bold;
  cursor:pointer;
}

button:hover{
  background:#1e40af;
}

.error{
  color:red;
  text-align:center;
}
</style>