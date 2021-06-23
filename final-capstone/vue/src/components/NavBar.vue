<template>
  <div>
    <nav>
      <ul class="nav-container">
        <header>
          <img class="golf-logo" src="../images/golf.svg" alt="Golf Bag Logo" />
        </header>
        <router-link class="nav-element" v-bind:to="{ name: 'home' }"
          >Home</router-link
        >
        <div
          class="nav-container"
          v-for="role in this.$store.state.user.authorities"
          v-bind:key="role.id"
          v-show="role.name != 'ROLE_GOLFER'"
        >
          <router-link
            v-if="role.name != 'ROLE_GOLFER'"
            class="nav-element"
            v-bind:to="{ name: 'logscores' }"
            >Log Scores</router-link
          >
          <router-link
            v-if="role.name != 'ROLE_GOLFER'"
            class="nav-element"
            v-bind:to="{ name: 'createleague' }"
            >Create League</router-link
          >
        </div>
        <router-link
          class="nav-element"
          v-bind:to="{ name: 'createteetime' }"
          v-if="$store.state.token != ''"
          >Create Tee Time
        </router-link>
        <router-link
          class="nav-element"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout, '{{ this.$store.state.user.username }}'
        </router-link>
      </ul>
      <!-- <div class="user-name">
        {{ this.$store.state.user.username }}
      </div> -->
    </nav>
  </div>
</template>

<script>
export default {};
</script>

<style>
* {
  margin: 0;
  padding: 0;
  list-style-type: none;
}
body {
  background-color: #5cdb95;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-weight: 600;
}
.nav-container {
  display: flex;
  width: 100%;

  margin: auto auto auto 5px;
  max-width: 1000px;
  justify-content: flex-start;
  text-align: center;
}
.nav-element {
  padding: 1rem 1rem 1rem;
  text-transform: uppercase;
  cursor: pointer;
  color: #05386b;
  min-width: 80px;
  width: 40%;
  margin: auto;
  text-decoration: none;
  justify-content: flex-start;
}

header {
  grid-area: header;
  display: flex;
  size: 100%;
}

header .golf-logo {
  display: flex;
  align-items: flex-end;
  padding: 5px;
  max-height: 45px;
  max-width: 45px;
}

.nav-element:hover {
  /* background-color: #379683; */
  background-image: url("../images/GrassForNavbar.png");
  background-size: 130% 130%;
  color: #edf5e1;
  animation: spring 50ms ease-out;
  text-shadow: 0 -1px 0 #8ee4af;
  font-weight: bold;
  border-radius: 5%;
}
.nav-element:active {
  transform: translateY(4px);
}

@keyframes spring {
  15% {
    -webkit-transform-origin: center center;
    -webkit-transform: scale(1.2, 1.1);
  }
  40% {
    -webkit-transform-origin: center center;
    -webkit-transform: scale(0.95, 0.95);
  }
  75% {
    -webkit-transform-origin: center center;
    -webkit-transform: scale(1.05, 1);
  }
  100% {
    -webkit-transform-origin: center center;
    -webkit-transform: scale(1, 1);
  }
}

.user-name {
  justify-content: flex-end;
}
</style>