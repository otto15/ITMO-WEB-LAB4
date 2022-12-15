<template>
  <div class="table-box">
    <div class="clean-button">
      <input @click="clearTable" type="button" value="Очистить таблицу" />
    </div>
    <div class="info-table">
      <table>
        <thead>
          <tr class="table-header">
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Checking time</th>
            <th>Result</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in items" :key="item.callingDate">
            <td class="x">{{ item.x }}</td>
            <td class="y">{{ item.y }}</td>
            <td class="r">{{ item.r }}</td>
            <td>{{ new Date(item.callingDate) }}</td>
            <td>{{ item.hitStatus }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "HitCheckTable",
  props: {
    items: Array,
  },
  methods: {
    ...mapActions({
      deleteAllHitChecks: "hitCheck/deleteAll",
    }),
    clearTable() {
      this.deleteAllHitChecks();
    },
  },
};
</script>

<style scoped></style>
