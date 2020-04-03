import {appAPI} from "../api/api";
import {stopSubmit} from "redux-form";

const SET_USER_DATA = 'SET-USER-DATA';

const initialState = {
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

const setUserDataAC = (name, isAuth) => ({type: SET_USER_DATA, payload: {name, isAuth}});

export const getAuthUserDataTC = () => (dispatch) => {
    appAPI.me().then(response => {
        if(response.data.statusCode === 200) {
            dispatch(setUserDataAC(response.data.data, true));
        }
    })
};

export const loginTC = (nickname, password) => (dispatch) => {
    appAPI.login(nickname, password).then(response => {
        if (response.data.statusCode === 200) {
            localStorage.setItem('userToken', response.data.data);
            dispatch(getAuthUserDataTC());
        }
        else {
            // const message = response.data.messages.length > 0 ? response.data.messages[0] : 'Unknown error.';
            const message = 'Unknown error';
            dispatch(stopSubmit('signIn', {_error: message}));
        }
    })
};

export const logoutTC = () => (dispatch) => {
    localStorage.removeItem('userToken');
    dispatch(setUserDataAC(null, false));
};

export const signUpTC = ({nickname, password, ...props}) => (dispatch) => {
    const formData = {nickname, password, ...props};
    appAPI.signUp(formData).then(response => {
        if (response.data.statusCode === 200) {
            localStorage.setItem('userToken', response.data.data);
            dispatch(loginTC(nickname, password));
        }
        else {
            const message = 'Registration error.'
            dispatch(stopSubmit('signUp', {_error: message}));
        }
    })
}