// src/api/api.ts
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://192.168.0.3:8081/api',
  timeout: 3000, // opcional
});

export default api;
