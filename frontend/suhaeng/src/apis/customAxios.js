import axios from 'axios';

const customAxios = axios.create({
    baseURL: 'http://localhost:80/',
});

export { customAxios };
