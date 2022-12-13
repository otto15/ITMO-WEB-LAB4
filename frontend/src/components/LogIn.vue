<template>
  <div class="container auth-template">
    <form @submit.prevent="login">
      <h2 class="mb-3">Login</h2>
      <div class="input">
        <label for="email">Username</label>
        <input
          v-model="user.username"
          class="form-control"
          type="text"
          placeholder="username"
        />
        <error-message
          v-if="errorMessageForUsername"
          :message="errorMessageForUsername"
        />
      </div>
      <div class="input">
        <label for="password">Password</label>
        <input
          v-model="user.password"
          class="form-control"
          type="password"
          placeholder="password"
        />
        <error-message
          v-if="errorMessageForPassword"
          :message="errorMessageForPassword"
        />
      </div>
      <div class="alternative-option mt-4">
        You don't have an account?
        <span @click="$router.push('/register')">Register</span>
      </div>
      <button type="submit" class="mt-4 btn-pers" id="login_button">
        Login
      </button>
      <div class="">
        <div
          v-if="message"
          class="error-message alert alert-danger"
          role="alert"
        >
          {{ message }}
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import AuthValidator from "@/services/authValidator";
import authMixin from "@/mixins/authMixin";

export default {
  mixins: [authMixin],
  methods: {
    login() {
      this.loading = true;

      const validationResult = AuthValidator.validateAuthUser(this.user);
      this.errorMessageForUsername = validationResult.msgForUsername;
      this.errorMessageForPassword = validationResult.msgForPassword;

      if (validationResult.msgForUsername || validationResult.msgForPassword) {
        return;
      }

      this.$store.dispatch("auth/login", this.user).then(
        () => {
          this.$router.push("/main");
        },
        (error) => {
          this.loading = false;
          this.message =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
        }
      );
    },
  },
};
</script>
