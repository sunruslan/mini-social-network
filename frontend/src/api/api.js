import * as axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:8080/',
    withCredentials: false,
});

export const usersAPI = {
    getUsers: (page = 1, pageSize = 10) => {
        return instance.get(`users?page=${page}&count=${pageSize}`)
            .then(response => response.data);
    },
    follow: (userId) => {
        return instance.post(`follow/${userId}`);
    },
    unfollow: (userId) => {
        return instance.delete(`follow/${userId}`);
    }
};

export const profileAPI = {
    getUserData: (userId) => {
        return instance.get(`profile/${userId}`);
    }
};

export const appAPI = {
    me: () => {
        return instance.get('auth/me');
    },

    login: (email, password) => {
        return instance.post('auth/login', {email, password});
    },

    logout: () => {
        return instance.delete('auth/login');
    }
};