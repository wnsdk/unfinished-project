import axios from "axios";

const apiInstance = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

apiInstance.interceptors.request.use((config) => {
  /** 세션에 저장된 access token을 헤더에 넣기 */
  config.headers["ACCESS-TOKEN"] = sessionStorage.getItem("Access-Token");
  return config;
});

export { apiInstance };
