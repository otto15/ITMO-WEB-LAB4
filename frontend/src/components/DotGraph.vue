<template>
  <svg
    id="svg-graph"
    @click="(event) => hitCheckByClick(event)"
    width="300px"
    height="300px"
  >
    <!-- Координатные оси -->
    <line x1="0" x2="300" y1="150" y2="150"></line>
    <line x1="150" x2="150" y1="0" y2="300"></line>
    <!--Стрелки-->
    <polygon points="150,0 145,15 155,15" stroke="black"></polygon>
    <polygon points="300,150 285,145 285,155" stroke="black"></polygon>
    <!--Прямоугольник в третьей четверти-->
    <polygon points="150,150 150,250 200,250 200,150"></polygon>
    <!--Четверть круга в первой четверти-->
    <path d="M150,200 A50,50 90 0,1 100,150 L 150,150 Z"></path>
    <!--Треугольник в четвертой четверти-->
    <polygon points="50,150 150,50 150,150"></polygon>
    <!-- Подписи к осям -->
    <text x="285" y="135">X</text>
    <text x="160" y="15">Y</text>
    <!-- Метки для значений R на оси X -->
    <line x1="50" x2="50" y1="143" y2="157"></line>
    <line x1="100" x2="100" y1="143" y2="157"></line>
    <line x1="200" x2="200" y1="143" y2="157"></line>
    <line x1="250" x2="250" y1="143" y2="157"></line>
    <!-- Метки для значений R на оси Y -->
    <line x1="143" x2="157" y1="50" y2="50"></line>
    <line x1="143" x2="157" y1="100" y2="100"></line>
    <line x1="143" x2="157" y1="200" y2="200"></line>
    <line x1="143" x2="157" y1="250" y2="250"></line>
    <!-- Значения R на оси X -->
    <text x="40" y="138">-R</text>
    <text x="85" y="138">-R/2</text>
    <text x="190" y="138">R/2</text>
    <text x="245" y="138">R</text>
    <!-- Значения R на оси Y -->
    <text x="162" y="54">R</text>
    <text x="162" y="104">R/2</text>
    <text x="162" y="204">-R/2</text>
    <text x="162" y="254">-R</text>

    <circle
      v-for="dot in dots"
      :key="dot.callingDate"
      :cx="150 + 100 * (dot.x / dot.r)"
      :cy="150 - 100 * (dot.y / dot.r)"
      r="4"
      :class="dot.hitStatus === true ? 'green' : 'red'"
    />
  </svg>
</template>

<script>
import hitCheckSender from "@/mixins/hitCheckSender";
import Dot from "@/models/dot";
import $ from "jquery";

export default {
  name: "GotGraph",
  props: {
    dots: Array,
  },
  mixins: [hitCheckSender],
  methods: {
    hitCheckByClick(event) {
      let target = $("#svg-graph");
      let r = this.r;
      let x = Math.round(event.clientX - target.position().left);
      let y = event.clientY - target.position().top;
      this.dot.x = ((x - 150) / (100 / r)).toFixed(3);
      this.dot.y = ((y - 150 + $(window).scrollTop()) / (-100 / r)).toFixed(3);
      this.processCheck(new Dot(this.dot.x, this.dot.y, this.r));
    },
  },
};
</script>

<style scoped>
circle {
  stroke: black;
}
.green {
  fill: #569e76;
}
.red {
  fill: #b15e79;
}
</style>
