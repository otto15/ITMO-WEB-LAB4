<template>
  <div class="validation-info">
    <span>
      {{ message }}
    </span>
  </div>
</template>

<script>
export default {
  name: "error-message",
  props: {
    message: String,
  },
};
</script>

<style scoped>
.validation-info {
  display: flex;
  justify-content: center;
  color: red;
}
</style>