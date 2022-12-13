<template>
  <div class="container auth-template">
    <form @submit.prevent="register">
      <h2 class="mb-3">Register</h2>
      <div class="input">
        <label for="email">Username</label>
        <input
          v-model="user.username"
          class="form-control"
          type="text"
          placeholder="Ivan"
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
          placeholder="qwerty123"
        />
        <error-message
          v-if="errorMessageForPassword"
          :message="errorMessageForPassword"
        />
      </div>

      <div class="input">
        <label for="password">Repeat password</label>
        <input
          v-model="user.repeatedPassword"
          class="form-control"
          type="password"
          placeholder="qwerty123"
        />
        <error-message
          v-if="errorMessageForRepeatedPassword"
          :message="errorMessageForRepeatedPassword"
        />
      </div>

      <div class="alternative-option mt-4">
        Already have an account? <span @click="$router.push('/')">Login</span>
      </div>

      <button type="submit" id="register_button" class="mt-4 btn-pers">
        Register
      </button>
      <div
        v-if="message"
        class="error-message alert alert-danger"
        role="alert"
      >
          {{ message }}
      </div>
    </form>
  </div>
</template>

<script>
import authMixin from "@/mixins/authMixin";
import AuthValidator from "@/services/authValidator";

export default {
  mixins: [authMixin],
  methods: {
    register() {
      this.loading = true;

      const validationResult = AuthValidator.validateAuthUser(this.user);
      this.errorMessageForUsername = validationResult.msgForUsername;
      this.errorMessageForPassword = validationResult.msgForPassword;
      this.errorMessageForRepeatedPassword =
        validationResult.msgForRepeatedPassword;

      if (
        validationResult.msgForUsername ||
        validationResult.msgForPassword ||
        validationResult.msgForRepeatedPassword
      ) {
        return;
      }

      this.$store.dispatch("auth/register", this.user).then(
        () => {
          this.$router.push("/");
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

<style scoped></style>
