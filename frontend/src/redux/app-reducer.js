import {appAPI} from "../api/api";
import {stopSubmit} from "redux-form";

const SET_USER_DATA = 'SET-USER-DATA';

const initialState = {
    userId: null,
    email: null,
    name: null,
    isAuth: false
};

export const appReducer = (state=initialState, action) => {
    switch (action.type) {
        case SET_USER_DATA:
            return {
                ...state,
                ...action.payload,
            };
        default:
            return state;
    }
};

const setUserDataAC = (userId, email, name, isAuth) => ({type: SET_USER_DATA, payload: {userId, email, name, isAuth}});

export const getAuthUserDataTC = () => (dispatch) => {
    appAPI.me().then(response => {
        if(response.data.resultCode === 0) {
            let {id, email, login} = response.data.data;
            dispatch(setUserDataAC(id, email, login, true));
        }
    })
};

export const loginTC = (email, password) => (dispatch) => {
    appAPI.login(email, password).then(response => {
        if (response.data.resultCode === 0) {
            dispatch(getAuthUserDataTC());
        }
        else {
            const message = response.data.messages.length > 0 ? response.data.messages[0] : 'Unknown error.';
            dispatch(stopSubmit('login', {_error: message}));
        }
    })
};

export const logoutTC = () => (dispatch) => {
    appAPI.logout().then(response => {
        if (response.data.resultCode === 0) {
            dispatch(setUserDataAC(null, null, null, false));
        }
    })
};
