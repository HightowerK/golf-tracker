<template>
  <!-- this conforms more to the add user score to round, moving most of the functionality to create tee time -->
  <div class="add-round-main">
    <div class="input-section-personal">
      <h2 class="new-score-header">ADD A NEW SCORE</h2>
      <div class="form-container">
        <form v-on:submit.prevent="saveNewRound">
          SCORE
          <input class="score" type="number" v-model="round.score.score" />
          <br />
          DATE
          <input class="round-date" type="date" v-model="round.tee_date" />
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
          <button class="add-score-btn">ADD SCORE</button>
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
      allCourses: {},
      round: {
        score: {
          score: 0,
        },
      },
    };
  },

  methods: {
    saveNewRound() {
      console.log(this.round);
      golferService
        .addPersonalRound(this.$store.state.user.id, this.round)
        .then((response) => {
          if (response.status === 201) {
            alert("New Score Added!");
          }
        })
        .catch((err) => {
          console.error(err + " Problem Adding Score!");
        });
    },
  },

  created() {
    golferService.getAllCourses().then((courseData) => {
      this.allCourses = courseData.data;
    });
  },
};
</script>

<style>
.form-container {
  overflow: auto;
  max-height: 70vh;
}

.input-section-personal {
  display: flex;
  max-width: 50vw;
  margin: 10px auto;
  padding: 16px;
  background: #8ee4af;
  flex-direction: column;
}
.input-section-personal h2.new-score-header {
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
.input-section-personal .score,
.input-section-personal .course-name,
.input-section-personal .round-date {
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

/* .input-section input[type="submit"], */
.input-section-personal .add-score-btn {
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
.input-section-personal .add-score-btn:hover {
  background: #064a8d;
  cursor: pointer;
}
</style>