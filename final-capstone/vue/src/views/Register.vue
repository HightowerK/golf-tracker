<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create A Golfer Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="username-container">
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control-username"
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
          class="form-control-password"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control-password"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
      </div>
      <div class="signin-container">
        <div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">
            Create Account
          </button>
        </div>
        <router-link class="router-text" :to="{ name: 'login' }"
          >Have an account?</router-link
        >
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
body {
  background-image: url("https://images.pexels.com/photos/5865/healthy-spring-young-green-5865.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940");
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
.form-register {
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

.signin-container .btn {
  background-color: white;
  padding: 5px;
  border-block-style: none;
  border-inline-style: none;
}

.signin-container .btn:hover {
  background-color: #8ee4af;
  color: white;
  cursor: pointer;
}

.form-control-password {
  margin-right: 5px;
}
</style>