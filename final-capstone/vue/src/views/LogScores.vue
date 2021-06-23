<template>
  <div class="log-scores">
    <!-- <nav-bar /> -->
    <add-league-rounds />
  </div>
</template>

<script>
import AddLeagueRounds from "../components/AddLeagueRounds.vue";
// import NavBar from "../components/NavBar.vue";
export default {
  components: { AddLeagueRounds },

  // created() {
  //   this.$router.push("/logscores");
  // },
};
</script>

<style>
</style>