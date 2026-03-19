<template>
  <div v-if="show" class="overlay">
    <div class="modal">
      <button class="close" @click="$emit('close')">✕</button>
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: ["show"],
};
</script>

<style>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
}

.modal{
  position:relative;
  background:white;
  padding:25px;
  border-radius:10px;
  min-width:320px;
  box-shadow:0 10px 25px rgba(0,0,0,0.2);
}

.close{
  position:absolute;
  top:10px;
  right:10px;
  background:#ef4444;
  color:white;
  border:none;
  border-radius:50%;
  width:28px;
  height:28px;
  cursor:pointer;
  font-weight:bold;

  display:flex;
  align-items:center;
  justify-content:center;
  padding:0;
  line-height:1;
}
</style>
