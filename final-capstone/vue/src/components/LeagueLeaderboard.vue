<template>
  <div>
    <h1 class="leaderboard-header">LEAGUE LEADERBOARD</h1>

    <div
      class="leaderboard-container"
      v-for="league in allLeaderboards.leagueList"
      v-bind:key="league.id"
    >
      <h2 class="league-name">{{ league.league_name }}</h2>
      <div class="table-container">
        <table class="leaderboard-table">
          <thead>
            <tr>
              <th class="header-name">NAME</th>
              <th class="header-score">SCORE</th>
            </tr>
          </thead>
          <div v-for="user in league.userList" v-bind:key="user.id">
            <div class="name-score">
              <tbody>
                <tr>
                  <td class="name-section">
                    <div>{{ user.last_name }}, {{ user.first_name }}</div>
                  </td>

                  <td class="score-section">
                    <div v-for="score in user.scoreList" v-bind:key="score.id">
                      {{ score.score }}
                    </div>
                  </td>
                </tr>
              </tbody>
            </div>
          </div>
        </table>
      </div>
    </div>

    <!-- <div v-for="scores in allLeaderboards.scoreList" v-bind:key="scores.id">
      <p>{{ scores.score }}</p>
    </div> -->

    <!-- <pre>{{ allLeaderboards }}</pre> -->
  </div>
</template>

<script>
import golferService from "@/services/GolferService";
export default {
  data() {
    return {
      allLeaderboards: [],
    };
  },

  created() {
    golferService
      .getLeaderboardById(this.$store.state.user.id)
      .then((leaderboardData) => {
        this.allLeaderboards = leaderboardData.data;
      });
  },

  methods: {
    // getYourLeagueLeaderboard() {
    //   golferService
    //     .getLeaderboardById(this.$store.state.user.id)
    //     .then((leaderboardData) => {
    //       this.allLeaderboards = leaderboardData.data;
    //     });
    // },
  },
};
</script>

<style>
.leaderboard-header {
  border-bottom: 5px solid currentColor;
  border-radius: 10%;
  padding: 5px;
}

h2.league-name {
  border-bottom: thin dashed currentColor;
  margin-left: auto;
  margin-right: auto;
  padding: 5px;
}

.leaderboard-container {
  display: flex;
  flex-direction: column;
  width: 95%;
  max-width: 95%;
  max-height: 80%;
  padding: 15px;
}

.leaderboard-container table {
  display: flex;
  flex-direction: column;
  background-color: #edf5e1;
}

thead tr {
  display: flex;
  justify-content: space-between;
}

/* div.name-score {
  justify-content: space-around;
} */

td.name-section,
td.score-section {
  width: 100%;
  justify-content: space-around;
}

.leaderboard-table {
  border-collapse: collapse;
  margin: 25px 0;
  font-size: 0.9em;
  min-width: 400px;
  box-shadow: 5px 7px 10px rgba(0, 0, 0, 0.15);
  width: 100%;
  overflow-x: auto;
  overflow-y: auto;
}

.leaderboard-table thead {
  display: table-header-group;
}

.table-container {
  height: 100vh;
  overflow: auto;
}

.leaderboard-table thead tr {
  background-color: #05386b;
  color: #edf5e1;
  text-align: left;
  font-weight: bold;
  width: 100%;
}

.leaderboard-table th,
.leaderboard-table td {
  padding: 12px 15px;
}

/* tbody {
  display: block;
  margin: auto 0;
} */

.leaderboard-table tbody tr {
  border-bottom: 1px solid #379683;
}

/* .leaderboard-table tbody tr:nth-of-type(even) {
  background-color: #edf5e1;
} */

.leaderboard-table tbody tr:last-of-type {
  border-bottom: 0.1rem solid #379683;
}
</style>