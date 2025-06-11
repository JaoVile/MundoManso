// app/api/api.js
import axios from 'axios';

// Substitua pelo IP da sua máquina na rede local e porta do backend.
// Exemplo: 'http://192.168.1.10:8080/api'
// Se seu back expõe endpoints em /api, inclua; se for raiz, ajuste.
const a_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: a_URL,
  // se precisar de headers padrão, auth, etc, configure aqui
});

export default api;
