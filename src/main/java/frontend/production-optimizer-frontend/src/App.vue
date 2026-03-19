<script>
import Header from "./components/Header.vue"

export default {
  components: {
    Header
  }
}
</script>

<template>
  <Header v-if="!$route.meta.hideHeader" />
  <router-view />
</template>

<style>
body{
  margin:0;
  font-family:Arial;
}

.container{
  padding:20px;
}
</style>