<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">
        Already A Golfer? Please Sign In
      </h1>
      <div class="alert alert-danger" role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
        Thank you for registering, please sign in.
      </div>
      <div class="username-container">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
      </div>

      <div class="password-container">
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
      </div>

      <div class="signin-container">
        <div>
          <button class="signin-btn" type="submit">Sign in</button>
        </div>
        <router-link class="router-text" :to="{ name: 'register' }"
          >Need an account?</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
body {
  background-color: #5cdb95;
  background-size: 100%;
}

.text-center {
  color: white;
  display: flex;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.h3 {
  font-size: 50px;
}

.sr-only {
  padding-right: 10px;
  font-size: 30px;
}
.form-signin {
  margin-left: auto;
  margin-right: auto;
}

.password-container {
  padding-top: 15px;
}

.signin-container {
  padding-top: 15px;
  font-size: 20px;
  text-decoration: none;
}

.signin-container,
.router-text {
  text-decoration: none;
  color: white;
}

.router-text:hover {
  text-decoration: underline;
}

.signin-container div {
  padding-bottom: 10px;
}

.signin-container div .signin-btn {
  background-color: white;
  padding: 5px;
  border-block-style: none;
  border-inline-style: none;
}

.signin-container div .signin-btn:hover {
  background-color: #8ee4af;
  color: white;
  cursor: pointer;
}
</style>