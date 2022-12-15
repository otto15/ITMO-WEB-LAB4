import eventBus from "@/common/eventBus";
import axiosInstance from "./api";
import TokenService from "./tokenService";

const publicUrls = ["api/auth/login", "api/auth/register"];
let refresh = false;

const setup = (store) => {
  axiosInstance.interceptors.request.use(
    (config) => {
      const token = TokenService.getLocalAccessToken();
      const tokenType = TokenService.getTokenType();
      console.log(token);
      if (token && tokenType) {
        config.headers["Authorization"] = tokenType + " " + token;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );
  axiosInstance.interceptors.response.use(
    (res) => {
      return res;
    },
    async (error) => {
      const originalConfig = error.config;
      const authRequired = !publicUrls.includes(originalConfig.url);
      if (authRequired && error.response.status === 401 && !refresh) {
        refresh = true;

        try {
          const response = await axiosInstance.post("api/auth/refresh", {
            refreshToken: TokenService.getLocalRefreshToken(),
          });

          const { status, data } = response;
          if (status === 200) {
            originalConfig._retry = true;

            store.dispatch("auth/refreshToken", data.accessToken);
            TokenService.updateLocalAccessToken(data.accessToken);

            return axiosInstance(originalConfig);
          }
        } catch (e) {
          eventBus.dispatch("logout");
          return Promise.reject(e);
        }
      }
      refresh = false;
      return Promise.reject(error);
    }
  );
};

export default setup;
