<template>
  <div class="add-round-main">
    <div class="input-section-scores">
      <h2 class="new-score-header">LOG LEAGUE SCORES</h2>
      <div class="form-container">
        <form class="new-round-form" v-on:submit.prevent="saveNewRound">
          <!-- FIRST NAME
        <input class="name" type="text" v-model="round.first_name" />
        <br />
        LAST NAME <input class="name" type="text" v-model="round.last_name" />
        <br /> -->
          USER
          <select class="name" v-model="user.user_id">
            <option value="" disabled>Pick User</option>
            <option
              v-for="user in userList"
              v-bind:key="user.id"
              v-bind:value="user.id"
            >
              {{ user.last_name }}, {{ user.first_name }}
            </option>
          </select>
          LEAGUE
          <select class="league" v-model="round.league_id">
            <option value="" disabled>League</option>
            <option
              v-for="league in allLeagues"
              v-bind:key="league.league_id"
              v-bind:value="league.league_id"
            >
              {{ league.league_name }}
            </option>
          </select>
          <br />
          COURSE
          <select class="course-name" v-model="round.course_id">
            <option value="" disabled>Course</option>
            <option
              v-for="course in allCourses"
              v-bind:key="course.course_id"
              v-bind:value="course.course_id"
            >
              {{ course.course_name }}
            </option>
          </select>

          <br />
          SCORE
          <input class="score" type="number" v-model="round.score.score" />
          <br />
          DATE
          <input class="round-date" type="date" v-model="round.tee_date" />
          <br />
          <br />
          <button class="add-score-btn">ADD SCORE</button>
        </form>
      </div>
      <!-- <pre> {{userList}} </pre> -->
    </div>
  </div>
</template>

<script>
import golferService from "@/services/GolferService";
export default {
  data() {
    return {
      allCourses: {},
      league: {},
      allLeagues: {},
      user: {
        user_id: 0,
      },
      userList: [],
      round: {
        league_id: 0,
        tee_date: null,
        score: {
          score: 0,
        },
      },
    };
  },

  methods: {
    saveNewRound() {
      //const userID = parseInt(this.user.user_id);
      console.log(this.round);
      golferService
        //this could not work, need to pass in ID
        .addPersonalRound(this.user.user_id, this.round)
        .then((response) => {
          if (response.status === 201) {
            alert("New Score Added!");
            //this.$router.push("/logscores");
          }
        })
        .catch((err) => {
          console.error(err + " Problem Adding Score!");
        });
    },
  },

  created() {
    golferService
      .getAllUsers()
      .then((response) => {
        this.userList = response.data;
        console.log(response.data);
      })
      .catch((err) => {
        if (err) {
          alert("error getting users");
        }
      });
    golferService
      .getAllLeagues()
      .then((response) => {
        this.allLeagues = response.data;
      })
      .catch((err) => {
        if (err) {
          alert("error getting leagues");
        }
      }),
      golferService.getAllCourses().then((courseData) => {
        this.allCourses = courseData.data;
      });
  },
};
</script>

<style>
.add-round-main {
  padding-top: 1em;
}

.form-container {
  overflow: auto;
  max-height: 70vh;
}

.input-section-scores {
  display: flex;
  max-width: 50vw;
  margin: 10px auto;
  padding: 16px;
  background: #8ee4af;
  flex-direction: column;
}
.input-section-scores h2.new-score-header {
  background: #05386b;
  padding: 0.3em 0;
  font-size: 140%;
  font-weight: bold;
  text-align: center;
  color: #8ee4af;
  margin: -1em -16px 16px -16px;
}
/* .input-section input[type="email"],
.input-section input[type="number"],
.input-section input[type="search"],
.input-section input[type="time"],
.input-section input[type="url"], 
.input-section select */
.input-section-scores .league,
.input-section-scores .score,
.input-section-scores .course-name,
.input-section-scores .name,
.input-section-scores .round-date {
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  outline: none;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  width: 100%;
  background: #edf5e1;
  margin-bottom: 4%;
  border: 1px solid #ccc;
  padding: 1%;
  color: #05386b;
}

/* input.name,
input.score,
input.course-name,
input.round-date {
  width: 100%;
} */

/* .input-section input[type="submit"], */
.input-section-scores .add-score-btn {
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  width: 100%;
  padding: 3%;
  background: #05386b;
  border-top-style: none;
  border-right-style: none;
  border-left-style: none;
  color: #8ee4af;
  font-weight: 600;
}
/* .input-section input[type="submit"]:hover, */
.input-section-scores .add-score-btn:hover {
  background: #064a8d;
  cursor: pointer;
}
</style>