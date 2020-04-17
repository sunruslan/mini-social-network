import * as axios from "axios";

// const instance = axios.create({
//     baseURL: 'http://localhost:8080/'
//     // baseURL: 'https://social-network.samuraijs.com/api/1.0/',
//     // withCredentials: true,
//     // headers: {
//     //     "API-KEY": "51f03785-9ab7-406e-86a2-e400b05e81c5"
//     // }
// });

const createInstance = (isAuth=true) => {
    if (isAuth) {
        let token = localStorage.getItem('userToken');
        token = 'Bearer ' + token;
        return axios.create({
            baseURL: 'http://localhost:8080/',
            headers: {
                'Authorization': token
            }
        });
    }
    return axios.create({
        baseURL: 'http://localhost:8080/',
    });
};

export const usersAPI = {
    getUsers: (page = 1, pageSize = 10) => {
        const instance = createInstance();
        return instance.get(`users?page=${page}&count=${pageSize}`)
            .then(response => {
                return response.data});
    },
    follow: (username) => {
        const instance = createInstance();
        return instance.post(`follow/${username}`);
    },
    unfollow: (username) => {
        const instance = createInstance();
        return instance.delete(`follow/${username}`);
    },
    getFollowings: (page = 1, count = 10) => {
        const instance = createInstance();
        return instance.get(`followings?page=${page}&count=${count}`);
    }
};

export const profileAPI = {
    getUserData: (username) => {
        const instance = createInstance();
        return instance.get(`profile/${username}`);
    },
    getPosts: (nickname, page, count) => {
        const instance = createInstance();
        return instance.get(`posts/${nickname}?page=${page}&count=${count}`);
    },
    addPost: (title, content) => {
        const instance = createInstance();
        return instance.post(`posts`, {title, content});
    },
    deletePost: (postId) => {
        const instance = createInstance();
        return instance.delete(`posts/${postId}`);
    }
};

export const appAPI = {
    me: () => {
        const instance = createInstance();
        return instance.get('username');
    },

    login: (nickname, password) => {
        const instance = createInstance(false);
        return instance.get(`users/signin?nickname=${nickname}&password=${password}`);
    },

    // logout: () => {
    //     return instance.delete('auth/login');
    // },

    signUp: ({nickname, password, ...props}) => {
        const instance = createInstance(false);
        const newUser = {
            nickname: nickname,
            password: password,
            roles: [
                "ROLE_CLIENT"
            ],
            userProfile: {
                about: props.about ? props.about : '',
                gender: props.gender,
                location: props.location ? props.location : 'Russia',
                profilePicUrl: props.profilePicUrl ? props.profilePicUrl : 'https://www.logolynx.com/images/logolynx/97/97e88682fa82ed11f3bf96dcf8479635.png'
            }
        };
        return instance.post('users/signup', newUser);
    }
};