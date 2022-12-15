import { createRouter, createWebHistory } from "vue-router";
import MainView from "@/views/MainView";
import LoginView from "@/views/LoginView";
import RegisterView from "@/views/RegisterView";
import store from "@/store";

const routes = [
  {
    path: "/",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/main",
    name: "main",
    component: MainView,
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/main",
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  const publicPages = ["/", "/register"];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = store.state.auth.user;
  if (authRequired && !loggedIn) {
    next("/");
  } else if (!authRequired && loggedIn) {
    next("/main");
  } else {
    next();
  }
});

export default router;
