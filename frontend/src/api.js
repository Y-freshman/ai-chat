import axios from 'axios';

export default axios.create({
  baseURL: 'https://IP:端口',
  timeout: 60000,
  headers: {
    'Content-Type': 'application/json'
  }
});