import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:3001/api",
  timeout: 20000,
  headers: {
    "Content-Type": "application/json",
  },
});

export default axiosInstance;