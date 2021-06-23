<template>
  <div class="add-league-main">
    <div class="input-section-league">
      <h2 class="league-header">CREATE LEAGUE</h2>
      <div class="form-container">
        <form class="new-league-form" v-on:submit.prevent="saveNewLeague">
          LEAGUE NAME
          <input
            class="league-name"
            type="text"
            v-model="league.league_name"
            required
          />
          <br />
          SCORE TYPE
          <select v-model="league.score_type">
            <option value=""></option>
            <option
              v-for="league in allLeagues"
              v-bind:value="league.score_type"
              v-bind:key="league.score_type"
              required
            >
              {{ league.score_type }}
            </option>
          </select>

          COURSE
          <div id="selector">
            <select v-model="league.course.course_id">
              <option value=""></option>
              <option
                v-for="course in allCourses"
                v-bind:value="course.course_id"
                v-bind:key="course.course_id"
                required
              >
                {{ course.course_name }}
              </option>
            </select>
          </div>
          <button class="add-score-btn">ADD LEAGUE</button>
        </form>
        <button
          v-on:click="showForm = true"
          v-if="showForm === false"
          id="add-user-btn"
        >
          ADD USER TO LEAGUE
        </button>
        <button v-on:click="showForm = false" v-else id="add-user-btn">
          HIDE FORM
        </button>
      </div>
    </div>

    <!-- DROPDOWN FOR ADDING USERS TO LEAGUE -->

    <div v-show="showForm" id="add-user-section" class="input-section-league">
      <h2 class="league-header">USER</h2>
      <div class="form-container">
        <form class="new-league-form" v-on:submit.prevent="addUserToLeague">
          USER SELECT
          <select class="league-name" v-model="user.user_id">
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
          <select class="league-name" v-model="user_league.league_id">
            <option value="" disabled>OPTIONAL</option>
            <option
              v-for="league in allLeagues"
              v-bind:key="league.league_id"
              v-bind:value="league.league_id"
            >
              {{ league.league_name }}
            </option>
          </select>

          <button class="add-score-btn">SUBMIT</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import golferService from "@/services/GolferService";
export default {
  data() {
    return {
      showForm: false,
      allCourses: [],
      league: {
        league_name: "",
        score_type: "",
        course: {
          course_id: 0,
        },
      },
      userList: {},
      user: {},
      user_league: {},
      allLeagues: {},
    };
  },

  created() {
    golferService.getAllCourses().then((courseData) => {
      this.allCourses = courseData.data;
    });
    golferService.getAllUsers().then((response) => {
      this.userList = response.data;
    });
    golferService.getAllLeagues().then((response) => {
      this.allLeagues = response.data;
    });
  },

  methods: {
    addUserToLeague() {
      //ship userid and leagueid to server, return 201, catch 400
      golferService
        .addUserToLeague(this.user.user_id, this.user_league.league_id)
        .then((response) => {
          if (response.status === 201) {
            alert("User added to League!");
            (this.user = {}), (this.user_league = {});
          }
        })
        .catch((err) => {
          console.log(err);
          alert("User already in League");
        });
    },

    saveNewLeague() {
      // const newLeague = {
      //   league_name: this.league.league_name,
      //   course_id: this.league.course.course_id,
      //   score_type: this.league.score_type,
      // };
      golferService
        .createNewLeague(this.league)
        .then((response) => {
          if (response.status === 201) {
            alert("New League Added!");
            this.$router.push("/");
          }
        })
        .catch((err) => {
          console.error(err + " Problem Adding Score!");
        });
    },
  },
};
</script>

<style>
#add-user-btn {
  margin-top: 25px;
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

.add-league-main {
  padding-top: 1em;
}

.form-container {
  overflow: auto;
  max-height: 70vh;
}

.input-section-league {
  display: flex;
  max-width: 50vw;
  margin: 10px auto;
  padding: 16px;
  background: #8ee4af;
  flex-direction: column;
}
.input-section-league h2.league-header {
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
.input-section-league .league-name,
.input-section-league .score-type,
.input-section-league #selector select,
.input-section-league select {
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
  border-radius: 3%;
  padding: 1%;
  color: #05386b;
}

input.name,
input.score,
input.course-name,
input.round-date {
  width: 100%;
}

/* .input-section input[type="submit"], */
.input-section-league .add-score-btn {
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
.input-section-league .add-score-btn:hover,
#add-user-btn:hover {
  background: #064a8d;
  cursor: pointer;
}
</style>