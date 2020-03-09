import * as axios from "axios";

const instance = axios.create({
    baseURL: 'https://social-network.samuraijs.com/api/1.0/',
    withCredentials: true,
    headers: {
        "API-KEY": "51f03785-9ab7-406e-86a2-e400b05e81c5"
    }
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