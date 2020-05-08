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
    totalCount: 0,
    pageSize: 3
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
            dispatch(setPostsAC(response.data.data["_2"], page, response.data.data["_1"]));
        }
    })
};

export const addPostTC = (title, content) => (dispatch, getState) => {
    let nickname = getState().app.name;
    let page = getState().profilePage.currentPage;
    let pageSize = getState().profilePage.pageSize;
    console.log(page, pageSize);
    profileAPI.addPost(title, content).then(response => {
        if (response.data.statusCode === 200) {
            dispatch(getPostsTC(nickname, page, pageSize));
        }
    })
}

export const deletePostTC = (postId) => (dispatch, getState) => {
    let page = getState().profilePage.currentPage;
    const pageSize = getState().profilePage.pageSize;
    const nickname = getState().app.name;
    const totalCount = getState().profilePage.totalCount;
    if (page > Math.ceil((totalCount - 1) / pageSize)) {
        page -= 1;
    }
    if (page === 0) {
        page = 1;
    }
    profileAPI.deletePost(postId).then(response => {
        if (response.data.statusCode === 200) {
            dispatch(getPostsTC(nickname, page, pageSize));
        }
    })
}
