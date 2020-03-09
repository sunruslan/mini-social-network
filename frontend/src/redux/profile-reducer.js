import {profileAPI} from "../api/api";

const SET_PROFILE = 'SET-PROFILE';

const initialState = {
    profile: null,
    posts: []
};

export const profileReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_PROFILE:
            return {
                ...state,
                profile: action.profile
            };
        default:
            return state;
    }
};

const setProfileAC = (profile) => ({type: SET_PROFILE, profile});

export const getProfileTC = (userId) => (dispatch) => {
    profileAPI.getUserData(userId).then(response => {
        dispatch(setProfileAC(response.data));
    });
};