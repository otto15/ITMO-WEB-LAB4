import axios from 'axios';

const API_URL = "http://localhost:8090/api/auth/";
const LOGIN_POSTFIX = "signin";
const REGISTER_POSTFIX = "register";

class AuthService {
    login(user) {
        return axios.post(
            API_URL + LOGIN_POSTFIX,
            {
                username: user.username,
                password: user.password
            }
        )
        .then(
            response => {
                if (response.data.accessToken) {
                    localStorage.setItem('user', JSON.stringify(response.data))
                }
            }
        )
    }

    logout(user) {
        localStorage.removeItem(user);
    }

    register(user) {
        return axios.post(
            API_URL + REGISTER_POSTFIX,
            {
                username: user.username,
                password: user.password
            }
        );
    }
}