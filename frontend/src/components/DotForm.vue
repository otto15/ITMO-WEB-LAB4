<template>
  <form id="form" @submit.prevent="submit">
    <div id="values x_div">
      <input type="hidden" name="x" id="x_value" v-model="dot.x" />
      <div class="value-title">
        <p>Enter X:</p>
      </div>
      <div class="buttons x-buttons">
        <input
          class="x-dot"
          :id="'x' + i"
          :class="i === dot.x ? 'selected' : ''"
          :key="i"
          type="button"
          v-for="i in xRange"
          :value="i"
          @click="updateX"
        />
      </div>
      <div v-if="errorMessageForX" class="validation-info x-validation-info">
        <span>{{ errorMessageForX }}</span>
      </div>
    </div>
    <div id="values y_div">
      <div class="value-title">
        <p>Enter Y:</p>
      </div>
      <div class="y">
        <input
          v-model="dot.y"
          type="text"
          name="y"
          id="y_value"
          @input="(event) => limitLength(event, 10)"
        />
      </div>
      <div v-if="errorMessageForY" class="validation-info y-validation-info">
        <span>{{ errorMessageForY }}</span>
      </div>
    </div>
    <div id="values r_div">
      <div class="value-title">
        <p>Enter R:</p>
      </div>
      <input type="hidden" name="r" id="r_value" :value="r" />
      <div class="buttons r-buttons">
        <input
          :class="i === r ? 'selected' : ''"
          class="r-size"
          :id="'r' + i"
          @click="(event) => changeR(event)"
          :key="i"
          type="button"
          v-for="i in rRange"
          :value="i"
        />
      </div>
    </div>
    <div class="managing-buttons">
      <input type="submit" value="Check" />
      <input id="reset" type="reset" value="Reset" @click="cleanInput" />
    </div>
  </form>
</template>

<script>
import Dot from "@/models/dot";
import { range } from "@/services/util";
import DotValidator from "@/services/dotValidator";
import hitCheckSender from "@/mixins/hitCheckSender";

export default {
  name: "DotForm",
  mixins: [hitCheckSender],
  data() {
    return {
      rRange: range(1, 6),
      xRange: range(-5, 4),
      errorMessageForX: "",
      errorMessageForY: "",
    };
  },
  methods: {
    updateX(event) {
      if (this.dot.x === Number(event.target.value)) {
        this.dot.x = null;
        return;
      }
      this.dot.x = Number(event.target.value);
    },
    changeR(event) {
      this.$emit("update:r", Number(event.target.value));
    },
    limitLength(event, count) {
      if (event.target.value.indexOf(".") === -1) {
        return;
      }
      if (event.target.value.length - event.target.value.indexOf(".") > count) {
        event.target.value = event.target.value.slice(
          0,
          event.target.value.indexOf(".") + count + 1
        );
      }
    },
    cleanInput() {
      this.dot = { x: null, y: null };
      this.$emit("update:r", 1);
    },
    submit() {
      const validationResult = DotValidator.validateDot({
        x: this.dot.x,
        y: this.dot.y,
      });
      this.errorMessageForX = validationResult.msgForX;
      this.errorMessageForY = validationResult.msgForY;

      if (validationResult.msgForX || validationResult.msgForY) {
        return;
      }

      this.processCheck(new Dot(this.dot.x, this.dot.y, this.r));
    },
  },
};
</script>

<style scoped></style>
