<template>
  <div id="app">
    <div id="nav">
      <nav-bar v-if="isAuthenticated" />
      <!-- <router-link class="router-text" v-bind:to="{ name: 'home' }"
        >Home</router-link
      >&nbsp;|&nbsp;
      <router-link
        class="router-text"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      > -->
    </div>
    <router-view />
  </div>
</template>
<script>
import NavBar from "./components/NavBar.vue";
export default {
  components: { NavBar },
  computed: {
    isAuthenticated() {
      return this.$store.state.token;
    },
  },
};
</script>
<style >
#nav .router-text {
  text-decoration: none;
  color: white;
}

#nav .router-text:hover {
  background-color: #8ee4af;
  color: white;
  cursor: pointer;
}
</style>
