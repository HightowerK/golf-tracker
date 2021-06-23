<template>
  <div class="tee-time-main">
    <div class="input-section-teetime">
      <h2 class="tee-time-header">CREATE TEE TIMES</h2>
      <div class="form-container">
        <form class="tee-time-form" v-on:submit.prevent="createTeeTime">
          <!--league, course, time, date -->
          LEAGUE
          <select v-model="round.league_id">
            <option value="" disabled>OPTIONAL</option>
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
          <select v-model="round.course_id">
            <option value="" disabled>COURSE</option>
            <option
              v-for="course in allCourses"
              v-bind:key="course.course_id"
              v-bind:value="course.course_id"
            >
              {{ course.course_name }}
            </option>
          </select>
          TIME <input type="time" v-model="round.tee_time" /> DATE
          <input type="date" v-model="round.tee_date" />
          <button>SCHEDULE</button>
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
      allCourses: [],
      allLeagues: [],
      round: {},
    };
  },

  created() {
    golferService.getAllCourses().then((courseData) => {
      this.allCourses = courseData.data;
    }),
      golferService.getAllLeagues().then((response) => {
        this.allLeagues = response.data;
      });
  },

  methods: {
    createTeeTime() {
      console.log(this.round);
      golferService
        .addNewRound(this.round, this.$store.state.user.id)
        .then((response) => {
          if (response.status === 201) {
            alert("Tee Time Scheduled!");
            this.$router.push("/");
          }
        })
        .catch((error) => {
          console.error(error + "problem scheduling time");
        });
    },
  },
};
</script>

<style>
.tee-time-main {
  padding-top: 1em;
}

.form-container {
  overflow: auto;
  max-height: 70vh;
}

.input-section-teetime {
  display: flex;
  max-width: 50vw;
  margin: 10px auto;
  padding: 16px;
  background: #8ee4af;
  flex-direction: column;
}
.input-section-teetime h2.tee-time-header {
  background: #05386b;
  padding: 0.3em 0;
  font-size: 140%;
  font-weight: bold;
  text-align: center;
  color: #8ee4af;
  margin: -1em -16px 16px -16px;
}

.input-section-teetime select,
.input-section-teetime input {
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

.input-section-teetime button {
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

.input-section-teetime button:hover {
  background: #064a8d;
  cursor: pointer;
}
</style>