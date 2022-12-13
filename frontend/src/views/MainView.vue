<template>
  <div class="wrapper">
    <div class="wrap-content">
      <div class="sender">
        <div class="in-sender graph">
          <DotGraph :r="r" :dots="getDotsByR" />
        </div>
        <div class="in-sender form-box">
          <DotForm :r="r" @update:r="setChosenR" />
        </div>
      </div>
      <HitCheckTable :items="hitChecks" />
    </div>
  </div>
</template>
<script>
import DotGraph from "@/components/DotGraph";
import DotForm from "@/components/DotForm";
import HitCheckTable from "@/components/HitCheckTable";
import { mapState, mapMutations, mapActions, mapGetters } from "vuex";

export default {
  components: {
    DotForm,
    DotGraph,
    HitCheckTable,
  },
  methods: {
    ...mapMutations({
      setChosenR: "hitCheck/setChosenR",
    }),
    ...mapActions({
      getAllHitChecks: "hitCheck/getAllHitChecks",
    }),
  },
  computed: {
    ...mapGetters({
      getDotsByR: "hitCheck/getHitCheckByR",
    }),
    ...mapState({
      r: (state) => state.hitCheck.chosenR,
      hitChecks: (state) => state.hitCheck.hitChecks,
    }),
  },
  mounted() {
    this.getAllHitChecks()
  },
};
</script>
<style lang=""></style>
