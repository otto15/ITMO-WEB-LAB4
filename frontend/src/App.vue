<template>
  <project-info />
  <router-view />
</template>

<script>
import ProjectInfo from "@/components/ProjectInfo.vue";
import EventBus from "@/common/eventBus";
export default {
  components: {
    ProjectInfo,
  },
  methods: {
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/");
    },
  },
  mounted() {
    EventBus.on("logout", () => {
      this.logout();
    });
  },
  beforeUnmount() {
    EventBus.remove("logout");
  },
};
</script>

<style>
#app {
  font-family: monospace;
  font-size: 1rem;
}
</style>
