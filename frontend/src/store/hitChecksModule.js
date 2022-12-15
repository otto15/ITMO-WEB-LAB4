import HitCheckService from "@/services/hitCheckService";

export const hitCheck = {
  namespaced: true,
  state: {
    chosenR: 1,
    hitChecks: [],
  },
  actions: {
    processHitCheck({ dispatch }, dot) {
      HitCheckService.checkHit(dot).then(
        (response) => {
          dispatch("getAllHitChecks");
          return Promise.resolve(response);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
    },
    getAllHitChecks({ commit }) {
      HitCheckService.getAllHitChecks().then((hitChecks) => {
        commit("setHitChecks", hitChecks);
        return Promise.resolve(hitChecks);
      });
    },
    deleteAll({ commit }) {
      HitCheckService.deleteAllHitChecks().then(
        (response) => {
          return Promise.resolve(response);
        },
        (error) => {
          return Promise.reject(error);
        }
      );
      commit("setHitChecks", []);
    },
  },
  getters: {
    getHitCheckByR(state) {
      return [...state.hitChecks].filter(
        (hitCheck) => hitCheck.r === state.chosenR
      );
    },
  },
  mutations: {
    setHitChecks(state, hitChecks) {
      state.hitChecks = hitChecks;
    },
    setChosenR(state, r) {
      state.chosenR = r;
    },
  },
};
