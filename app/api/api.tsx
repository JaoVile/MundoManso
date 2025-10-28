// app/api/api.ts
import axios from 'axios';
import { Platform } from 'react-native';

// Emulador Android enxerga o "localhost" do PC por 10.0.2.2
const host = Platform.OS === 'android' ? '10.0.2.2' : 'localhost';

const api = axios.create({
baseURL: 'http://10.0.2.2:8081',
timeout: 10000,
  headers: { 'Content-Type': 'application/json' },
});

if (__DEV__) console.log('[API] baseURL =', api.defaults.baseURL);


export default api;
