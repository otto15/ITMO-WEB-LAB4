import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap/dist/css/bootstrap.min.css";
import "@/assets/css/main.css";
import components from "@/components/UI";
import setupInterceptors from "@/services/setupInterceptors";

const app = createApp(App);

components.forEach((component) => {
  app.component(component.name, component);
});

setupInterceptors(store);

app.use(store).use(router).mount("#app");
