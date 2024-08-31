import axios from 'axios';

const customXxxAxios = axios.create({
    baseURL: 'https://(백엔드 서버 주소)/api/Xxx/',
    headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'multipart/form-data',
    },
});

const customTestAxios = axios.create({
    baseURL: 'https://koreanjson.com/',
});

// $.interceptors.request.use((config) => {
//     config.headers['Access-Token'] = sessionStorage.getItem('Access-Token');
//     return config;
// });

export { customXxxAxios, customTestAxios };
