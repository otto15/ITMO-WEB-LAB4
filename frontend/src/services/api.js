import axios from "axios";

const API_URL = "http://localhost:8090/";

export default axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
