<template>
  <project-info />
  <router-view />
</template>

<script>
import ProjectInfo from '@/components/ProjectInfo.vue';
export default {
  components: {
    ProjectInfo
  }
}
</script>

<style>
#app {
  font-family: monospace;
  font-size: 1.0rem;
}
</style>
