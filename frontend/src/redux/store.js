import {applyMiddleware, combineReducers, createStore} from 'redux';
import usersReducer from './users-reducer';
import thunkMiddleWare from 'redux-thunk';
import {profileReducer} from "./profile-reducer";
import {reducer as formReducer} from 'redux-form';
import {appReducer} from "./app-reducer";

let reducers = combineReducers({
    usersPage: usersReducer,
    profilePage: profileReducer,
    app: appReducer,
    form: formReducer
});

let store = createStore(reducers, applyMiddleware(thunkMiddleWare));

window.store = store;

export default store;