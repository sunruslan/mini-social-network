import {profileAPI} from "../api/api";
import {stopSubmit} from "redux-form";

const SET_PROFILE = 'SET-PROFILE';
const TOGGLE_LOADING_PROFILE = 'TOGGLE-LOADING-PROFILE';
const SET_POSTS = 'SET-POSTS';

const initialState = {
    profile: null,
    posts: [],
    isFetching: true,
    currentPage: 1,
    totalCount: 0
};

export const profileReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_PROFILE:
            return {
                ...state,
                profile: action.profile
            };
        case TOGGLE_LOADING_PROFILE:
            return {
                ...state,
                isFetching: action.isFetching
            };
        case SET_POSTS:
            return {
                ...state,
                posts: action.posts,
                totalCount: action.totalCount,
                currentPage: action.page
            };
        default:
            return state;
    }
};

const setProfileAC = (profile) => ({type: SET_PROFILE, profile});

const toggleLoadingProfileAC = (isFetching) => ({type: TOGGLE_LOADING_PROFILE, isFetching});

const setPostsAC = (posts, page, totalCount) => ({type: SET_POSTS, posts, page, totalCount});

export const getProfileTC = (username) => (dispatch) => {
    dispatch(toggleLoadingProfileAC(true));
    profileAPI.getUserData(username).then(response => {
        dispatch(setProfileAC(response.data.data));
        dispatch(toggleLoadingProfileAC(false));
    });
};

export const getPostsTC = (nickname, page=1, count=10) => (dispatch) => {
    profileAPI.getPosts(nickname, page, count).then(response => {
        if (response.data.statusCode === 200) {
            dispatch(setPostsAC(response.data.data.value, page, response.data.data.key));
        }
    })
};

export const addPostTC = (nickname, title, content, page=1) => (dispatch) => {
    profileAPI.addPost(title, content).then(response => {
        if (response.data.statusCode === 200) {
            dispatch(getPostsTC(nickname, page));
        }
    })
}

export const deletePostTC = (nickname, postId, page=1) => (dispatch) => {
    profileAPI.deletePost(postId).then(response => {
        if (response.data.statusCode === 200) {
            dispatch(getPostsTC(nickname, page));
        }
    })
}
