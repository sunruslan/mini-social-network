import {usersAPI} from "../api/api";

const FOLLOW = 'FOLLOW';
const UNFOLLOW = 'UNFOLLOW';
const SET_USERS = 'SET-USERS';
const SET_PAGE = 'SET-PAGE';
const TOGGLE_FETCHING = 'TOGGLE-FETCHING';
const SET_TOTAL_COUNT = 'SET-TOTAL-COUNT';

const initialState = {
    users: [],
    totalUsersCount: 0,
    pageSize: 5,
    currentPage: 1,
    isFetching: true
};

const usersReducer = (state = initialState, action) => {
    switch (action.type) {
        case FOLLOW:
            return {
                ...state,
                users: state.users.map(u => {
                    if (u.nickname === action.username) {
                        return {...u, following: true};
                    }
                    return u;
                })
            };
        case UNFOLLOW:
            return {
                ...state,
                users: state.users.map(u => {
                    if (u.nickname === action.username) {
                        return {...u, following: false};
                    }
                    return u;
                })
            };
        case SET_USERS:
            return {
                ...state,
                users: action.users
            };
        case SET_PAGE:
            return {
                ...state, currentPage: action.page
            };
        case TOGGLE_FETCHING:
            return {
                ...state, isFetching: action.isFetching
            };
        case SET_TOTAL_COUNT:
            return {
                ...state,
                totalUsersCount: action.totalUsersCount
            };
        default:
            return state;
    }
};

export const followAC = (username) => ({type: FOLLOW, username});

export const unfollowAC = (username) => ({type: UNFOLLOW, username});

export const setUsersAC = (users) => ({type: SET_USERS, users});

export const setPageAC = (page) => ({type: SET_PAGE, page});

export const toggleFetchingAC = (isFetching) => ({type: TOGGLE_FETCHING, isFetching});

export const setTotalCountAC = (totalUsersCount) => ({type: SET_TOTAL_COUNT, totalUsersCount});

export const getUsersTC = (page, pageSize) => (dispatch) => {
    dispatch(toggleFetchingAC(true));
    usersAPI.getUsers(page, pageSize)
        .then(data => {
            dispatch(setUsersAC(data.data.value));
            dispatch(setTotalCountAC(data.data.key));
            dispatch(toggleFetchingAC(false));
        })
};

export const followTC = (username) => (dispatch) => {
    usersAPI.follow(username)
        .then(response => {
            if (response.data.statusCode === 200) {
                dispatch(followAC(username));
            }
        });
};

export const unfollowTC = (username) => (dispatch) => {
    usersAPI.unfollow(username)
        .then(response => {
            if (response.data.statusCode === 200) {
                dispatch(unfollowAC(username));
            }
        });
};

export default usersReducer;