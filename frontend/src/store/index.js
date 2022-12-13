import { createStore } from "vuex";
import { auth } from "@/store/authModule";
import { hitCheck } from "@/store/hitChecksModule";

export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    auth,
    hitCheck,
  },
});
