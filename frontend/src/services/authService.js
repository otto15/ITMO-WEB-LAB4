import TokenService from "@/services/tokenService";
import api from "@/services/api";

const LOGIN_POSTFIX = "api/auth/login";
const REGISTER_POSTFIX = "api/auth/register";

class AuthService {
  login(user) {
    return api
      .post(LOGIN_POSTFIX, {
        username: user.username,
        password: user.password,
      })
      .then((response) => {
        if (response.data.accessToken && response.data.refreshToken) {
          TokenService.setUser(response.data);
        }
        return response.data;
      });
  }

  logout() {
    TokenService.removeUser();
  }

  register(user) {
    return api.post(REGISTER_POSTFIX, {
      username: user.username,
      password: user.password,
    });
  }
}

export default new AuthService();
