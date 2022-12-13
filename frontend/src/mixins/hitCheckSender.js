import { mapActions } from "vuex";

export default {
  data() {
    return {
      dot: { x: null, y: null },
    };
  },
  props: {
    r: Number,
  },
  methods: {
    ...mapActions({
      processCheck: "hitCheck/processHitCheck",
    }),
  },
};
