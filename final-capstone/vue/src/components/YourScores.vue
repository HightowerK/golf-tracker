<template>
  <div class="scores-container">
    <h1 class="score-header">YOUR SCORES</h1>
    <br />
    <!-- <pre>{{ this.$store.state.user }}</pre> -->

    <!-- <div>
      <label for="score">Scores: </label>
   <input
        type="text"
        class="locationFilter"
        placeholder="Search for score"
        v-model="filter.rounds.score"
      />
    </div> -->
    <table>
      <th class="score-table-header">Latest Scores</th>
      <th class="score-table-header">Score Date</th>
    </table>
    <div v-for="score in yourScores.rounds" v-bind:key="score.id">
      <table class="your-scores-table">
        <tr>
          <td class="score-table-data">{{ score.score.score }}</td>
          <td class="score-table-data">{{ score.tee_date }}</td>
        </tr>
      </table>
      <!-- <p>{{ score.score.score }}, {{ score.tee_date }}</p> -->
    </div>
    <br />
    <h2 class="round-header">UPCOMING ROUNDS</h2>
    <table>
      <th class="upcoming-round-header">Time</th>
      <th class="upcoming-round-header">Date</th>
      <th class="upcoming-round-header">Course</th>
    </table>
    <div
      class="round-data-container"
      v-for="round in upcomingRounds"
      v-bind:key="round.id"
    >
      <table>
        <td class="upcoming-round-data">{{ round.round.tee_time }}</td>
        <td class="upcoming-round-data">{{ round.round.tee_date }}</td>
        <td class="upcoming-round-data">{{ round.course.course_name }}</td>
      </table>
    </div>
    <br />

    <div class="show-add-score">
      <button v-on:click="showForm = true" v-if="showForm === false">
        ADD SCORE
      </button>
      <button v-on:click="showForm = false" v-else>HIDE FORM</button>
      <add-round class="add-round" v-if="showForm === true" />
    </div>

    <br />
    <br />
    <!-- <pre> {{ upcomingRounds }} </pre> -->
    <!-- <pre> {{filterScores}} </pre> -->
    <!-- <div 
    v-for="score in filterScore"
    v-bind:key="score.id"
    >
    <pre> {{score}} </pre>
    </div> -->
  </div>
</template>

<script>
import golferService from "@/services/GolferService";
import AddRound from "./AddRound.vue";
export default {
  components: { AddRound },
  data() {
    return {
      yourScores: {},
      upcomingRounds: {},
      showForm: false,
    };
  },

  // created() {
  //   golferService.getAllScores().then((scoreData) => {
  //     this.yourScores = scoreData.data;
  //   });
  // },

  created() {
    golferService.getScoresById(this.$store.state.user.id).then((scoreData) => {
      this.yourScores = scoreData.data;
    }),
      golferService
        .upcomingRounds(this.$store.state.user.id)
        .then((response) => {
          this.upcomingRounds = response.data;
        })
    //this.filterScores = this.filterSelfScores();
  },

  computed: {
    // sortYourScores() {
    //   return this.yourScores.orderBy(this.yourScores.round, "tee_date", "desc");
    // },

    // filterScore(){
    //   let allScores = this.yourScores;
    //   console.log(allScores)
    //   let currentDate = new Date();
    //   currentDate.value = Date.now();
    //   this.filterScore.filter((score) => {
    //     score.tee_date < currentDate;
    //   })
    //   return allScores;
    // }
  },
};
</script>

<style>
.score-container {
  display: flex;
}

.score-header {
  border-bottom: 5px solid currentColor;
  border-radius: 10%;
  padding: 5px;
  display: flex;
  flex-direction: column;
}

.score-table-header {
  margin-left: auto;
  margin-right: auto;
}

.score-table-data {
  padding-left: 3.2em;
  padding-right: 2em;
}

.show-add-score button {
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  width: 100%;
  padding: 3%;
  background: #379683;
  border-top-style: none;
  border-right-style: none;
  border-left-style: none;
  color: #edf5e1;
  font-weight: 600;
}

.show-add-score button:hover {
  background: #064a8d;
  cursor: pointer;
}

.round-header {
  border-bottom: thin dashed currentColor;
  margin-bottom: 4px;
}
table {
  table-layout: fixed;
  width: 25vw;
}

table th.upcoming-round-header {
  width: 100%;
  padding-left: 2em;
  padding-right: 2em;
}

table td.upcoming-round-data {
  padding-left: 1em;
  padding-right: 1em;
}
</style>