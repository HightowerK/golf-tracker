<template>
  <div class="home">
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <!-- <div class="loading" v-if="isLoading">
      <img
        src="../images/SwimmingGolfBall.gif"
        alt="Swimming Golf Ball Loader"
      />
    </div> -->
    <!-- <header>
      <img class="golf-logo" src="../images/golf.svg" alt="Golf Bag Logo" />
    </header> -->
    <!-- <nav-bar class="nav-bar" /> -->
    <!-- <h1>Home</h1>
    <p>You must be authenticated to see this</p> -->

    <your-scores class="your-scores" />
    <league-leaderboard class="league-leaderboard" />
    <locate-course class="locate-course" />

    <footer>Golf Tracker &copy; 2021</footer>
  </div>
</template>

<script>
import LeagueLeaderboard from "../components/LeagueLeaderboard.vue";
import LocateCourse from "../components/LocateCourse.vue";
import YourScores from "../components/YourScores.vue";
export default {
  components: { YourScores, LeagueLeaderboard, LocateCourse },
  name: "home",

  data() {
    return {
      isLoading: false,
    };
  },

  // created() {
  //   setInterval(() => {
  //     this.isLoading = false;
  //   }, 3000);
  // },
};
</script>

<style>
.loading {
  position: fixed;
  left: 150px;
  top: 150px;
}

body {
  background-color: #5cdb95;
  background-size: 100%;
  color: #05386b;
}

.home {
  display: grid;
  width: 100%;
  height: 100vh;
  grid-template-columns: 1fr 2fr 1fr;
  grid-template-rows: 0.25fr 3fr 0.25fr;
  grid-template-areas:
    /* "nav-bar nav-bar nav-bar" */
    "your-scores league-leaderboard locate-course"
    "footer footer footer";
  gap: 10px;
  padding-top: 1em;
  grid-auto-flow: column;
}

.nav-bar {
  grid-area: nav-bar;
  display: flex;
  align-items: flex-end;
}

.your-scores {
  grid-area: your-scores;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 90vh;
}

.league-leaderboard {
  grid-area: league-leaderboard;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 90vh;
}

.locate-course {
  grid-area: locate-course;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 90vh;
}

footer {
  grid-area: footer;
  display: flex;
  justify-content: center;
  align-items: center;
  max-height: 10%;
}

.your-scores,
.league-leaderboard,
.locate-course,
footer {
  background-color: #8ee4af;
  border-radius: 5px;
  padding: 15px;
}

.loading {
  margin-left: auto;
  margin-right: auto;
}

@media screen and (max-width: 800px) {
  body {
    grid-template-columns: 1fr;
    grid-template-areas:
      "your-scores"
      "league-leaderboard"
      "locate-course"
      "footer";
  }
}
</style>


