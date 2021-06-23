<template>
  <div class="create-league">
    <!-- <nav-bar /> -->
    <create-new-league />
  </div>
</template>

<script>
import CreateNewLeague from "../components/CreateNewLeague.vue";
// import NavBar from "../components/NavBar.vue";
export default {
  components: { CreateNewLeague },

  created() {
    // this.$router.push("/createleague");
  },
};
</script>

<style>
</style>