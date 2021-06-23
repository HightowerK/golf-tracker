<template>
  <div class="add-course-main">
    <div class="input-section-course">
      <div class="form-container">
        <form class="new-course-form" v-on:submit.prevent="saveNewCourse">
          <!-- <h2 class="course-header">ADD NEW COURSE</h2> -->
          <br />
          Course Name
          <input class="course-name" type="text" v-model="course.course_name" />
          <br />
          Holes
          <select class="holes" v-model="course.holes">
            <option value="18">18</option>
            <option value="9">9</option>
          </select>
          <br />
          Yardage
          <input
            class="yardage"
            type="number"
            min="1000"
            max="10000"
            v-model="course.yardage"
          />
          <br />
          Slope
          <input
            class="slope"
            type="number"
            min="55"
            max="155"
            v-model="course.slope"
          />
          <br />
          Rating
          <input
            class="rating"
            type="number"
            value="69.0"
            placeholder="69.0"
            v-model="course.rating"
          />
          <br />
          Par
          <input
            class="score-to-par"
            type="number"
            value="70"
            placeholder="70"
            v-model="course.score_to_par"
          />
          <br />
          <br />

          <h3>ADDRESS</h3>
          <br />
          Street
          <input class="street" type="text" v-model="course.address.street" />
          <br />
          City
          <input class="city" type="text" v-model="course.address.city" />
          <br />
          State (Abbreviation)
          <input class="state" type="text" v-model="course.address.region" />
          <br />
          Zip Code
          <input class="postal" type="number" v-model="course.address.postal" />
          <br />
          Country (Abbreviation)
          <input class="country" type="text" v-model="course.address.country" />
          <br />
          <br />

          <br />

          <button class="add-course-btn">ADD COURSE</button>
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
      course: {
        course_name: "",
        holes: "",
        yardage: 0,
        slope: 0.0,
        rating: 0.0,
        score_to_par: 0,
        address: {
          street: "",
          city: "",
          region: "",
          postal: "",
          country: "",
        },
      },
    };
  },

  methods: {
    saveNewCourse() {
      golferService
        .addNewCourse(this.course)
        .then((response) => {
          if (response.status === 201) {
            alert("New Course Added!");
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
.add-league-main {
  padding-top: 1em;
}

.form-container {
  overflow: auto;
  max-height: 70vh;
}

.input-section-course {
  display: flex;
  max-width: 50vw;
  margin: 10px auto;
  padding: 10px;
  background: #8ee4af;
  flex-direction: column;
}
.input-section-course h2.course-header {
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
/* .input-section .league-name, */
.input-section-course input,
.input-section-course select {
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
.input-section-course .add-course-btn {
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
.input-section-course .add-course-btn:hover {
  background: #064a8d;
  cursor: pointer;
}
</style>