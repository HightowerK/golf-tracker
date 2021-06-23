<template>
  <div class="locate-course-container">
    <h1 class="locate-header">LOCATE COURSE</h1>
    <br />

    <div class="input-section-course">
      <label for="region">BY STATE (Abbreviation) </label>
      <input
        type="text"
        class="locationFilter"
        placeholder="Search for course"
        v-model="filter.address.region"
      />
    </div>
    <div class="input-section-course">
      <label for="city">BY CITY </label>
      <input
        type="text"
        class="locationFilter"
        placeholder="Search for course"
        v-model="filter.address.city"
      />
    </div>

    <div class="input-section-course">
      <label for="course_name">BY COURSE NAME </label>
      <input
        type="text"
        class="locationFilter"
        placeholder="Search for course"
        v-model="filter.course.course_name"
      />
    </div>

    <div class="show-add-course">
      <button>
        <router-link
          style="text-decoration: none; color: #edf5e1"
          class="map-link"
          v-bind:to="{ name: 'course-map' }"
          target="_blank"
          v-on:click="showMap = !showMap"
          >VIEW ON MAP</router-link
        >
      </button>
      <div v-show="showMap" class="map-display">
        <map-view></map-view>
      </div>
    </div>

    <div
      v-for="course in filteredList"
      v-bind:key="course.id"
      v-show="filter.address.region"
    >
      <p>
        {{ course.course_name }} - {{ course.address.city }},
        {{ course.address.region }}
      </p>
    </div>

    <div
      v-for="course in filteredList"
      v-bind:key="course.id"
      v-show="filter.address.city"
    >
      <p>
        {{ course.course_name }} - {{ course.address.city }},
        {{ course.address.region }}
      </p>
    </div>

    <div
      v-for="course in filteredList"
      v-bind:key="course.id"
      v-show="filter.course.course_name"
    >
      <p>
        {{ course.course_name }} - {{ course.address.city }},
        {{ course.address.region }}
      </p>
    </div>
    <br />
    <div class="form-container">
      <div
        v-for="role in this.$store.state.user.authorities"
        v-bind:key="role.id"
      >
        <div class="show-add-course" v-show="role.name == 'ROLE_ADMIN'">
          <!-- <h2>ADD A NEW COURSE:</h2>
        <br /> -->
          <button v-on:click="showForm = true" v-if="showForm === false">
            ADD COURSE
          </button>
          <button v-on:click="showForm = false" v-else>HIDE FORM</button>
          <add-new-course v-if="showForm === true" class="add-new-course" />
        </div>
      </div>
    </div>

    <!-- <pre>{{ this.$store.state.user }}</pre> -->

    <!-- <div>

      <pre>{{ filteredList }}</pre>
    </div> -->
    <!-- <pre>{{ course }}</pre> -->
    <!-- <div class="courses" v-for="course in allCourses" :key="course.id"> -->
    <!-- {{ course.course_name }} - # of Holes: {{ course.holes }} -->
    <!-- </div> -->
  </div>
</template>

<script>
import golferService from "@/services/GolferService";
import AddNewCourse from "./AddNewCourse.vue";
import MapView from "../components/CourseOnMap";
export default {
  components: { AddNewCourse, MapView },
  data() {
    return {
      allCourses: [],
      singleCourse: {},
      showForm: false,
      showMap: false,
      filter: {
        course: {
          course_name: "",
          holes: 0,
          yardage: 0,
          slope: 0.0,
          rating: 0.0,
          score_to_par: 0,
        },
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

  created() {
    golferService.getAllCourses().then((courseData) => {
      this.allCourses = courseData.data;
    });
  },
  // methods: {
  //   getSingleCourse() {
  //     golferService.getCourseById(this.course.id).then((course) => {
  //       this.singleCourse = course.data;
  //     });
  //   },
  // },

  computed: {
    filteredList() {
      let filteredCourses = this.allCourses;
      if (this.filter.address.region != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.address.region
            .toLowerCase()
            .includes(this.filter.address.region.toLowerCase())
        );
      }
      if (this.filter.address.city != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.address.city
            .toLowerCase()
            .includes(this.filter.address.city.toLowerCase())
        );
      }

      if (this.filter.address.country != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.address.country
            .toLowerCase()
            .includes(this.filter.address.country.toLowerCase())
        );
      }

      if (this.filter.address.postal != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.address.postal
            .toLowerCase()
            .includes(this.filter.address.postal.toLowerCase())
        );
      }

      if (this.filter.address.street != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.address.street
            .toLowerCase()
            .includes(this.filter.address.street.toLowerCase())
        );
      }

      if (this.filter.course.course_name != "") {
        filteredCourses = filteredCourses.filter((course) =>
          course.course_name
            .toLowerCase()
            .includes(this.filter.course.course_name.toLowerCase())
        );
      }

      return filteredCourses;
    },
  },
};
</script>

<style>
div.input-section-course {
  display: flex;
  max-width: 100%;
  margin-top: -3px;
  margin-bottom: -3px;
  padding: 2px;
  background: #8ee4af;
  max-height: 100%;
  flex-direction: column;
}
/* .input-section h2.course-header {
  background: #05386b;
  padding: 0.3em 0;
  font-size: 140%;
  font-weight: bold;
  text-align: center;
  color: #8ee4af;
  margin: -1em -16px 16px -16px;
} */

.input-section-course input {
  -webkit-transition: all 0.3s ease-in-out;
  -moz-transition: all 0.3s ease-in-out;
  -ms-transition: all 0.3s ease-in-out;
  -o-transition: all 0.3s ease-in-out;
  outline: none;
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  width: 40vh;
  background: #edf5e1;
  margin-bottom: 4%;
  border: 1px solid #ccc;
  border-radius: 3%;
  padding: 1%;
  color: #05386b;
}

/* input.name,
input.score,
input.course-name,
input.round-date {
  width: 100%;
} */

.locate-course-container {
  display: flex;
  flex-direction: column;
  width: 25vw;
}

.locate-header {
  border-bottom: 5px solid currentColor;
  border-radius: 10%;
  padding: 5px;
}

router-link.map-link {
  text-decoration: none;
}

.show-add-course button {
  box-sizing: border-box;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  width: 100%;
  padding: 3%;
  margin-bottom: 5px;
  background: #379683;
  border-top-style: none;
  border-right-style: none;
  border-left-style: none;
  color: #edf5e1;
  font-weight: 600;
  text-decoration: none;
}

.show-add-course button:hover {
  background: #064a8d;
  color: #edf5e1;
  cursor: pointer;
}

button router-link a {
  text-decoration: none;
}
</style>