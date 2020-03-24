import {usersAPI} from "../api/api";

const FOLLOW = 'FOLLOW';
const UNFOLLOW = 'UNFOLLOW';
const SET_USERS = 'SET-USERS';
const SET_PAGE = 'SET-PAGE';
const TOGGLE_FETCHING = 'TOGGLE-FETCHING';
const SET_TOTAL_COUNT = 'SET-TOTAL-COUNT';

const initialState = {
    users:
        [
            // {id: 1, username: 'Kek', status: 'lol', location: 'Russia', followed: false},
            // {id: 2, username: 'Kek', status: 'lol', location: 'Russia', followed: false},
            // {id: 3, username: 'Kek', status: 'lol', location: 'Russia', followed: false}
        ],
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
                    if (u.id === action.id) {
                        return {...u, followed: true};
                    }
                    return u;
                })
            };
        case UNFOLLOW:
            return {
                ...state,
                users: state.users.map(u => {
                    if (u.id === action.id) {
                        return {...u, followed: false};
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

export const followAC = (userId) => ({type: FOLLOW, id: userId});

export const unfollowAC = (userId) => ({type: UNFOLLOW, id: userId});

export const setUsersAC = (users) => ({type: SET_USERS, users});

export const setPageAC = (page) => ({type: SET_PAGE, page});

export const toggleFetchingAC = (isFetching) => ({type: TOGGLE_FETCHING, isFetching});

export const setTotalCountAC = (totalUsersCount) => ({type: SET_TOTAL_COUNT, totalUsersCount});

export const getUsersTC = (page, pageSize) => (dispatch) => {
    dispatch(toggleFetchingAC(true));
    usersAPI.getUsers(page, pageSize)
        .then(data => {
            dispatch(setUsersAC(data.items));
            dispatch(setTotalCountAC(data.totalCount));
            dispatch(toggleFetchingAC(false));
        })
};

export const followTC = (userId) => (dispatch) => {
    usersAPI.follow(userId)
        .then(response => {
            if (response.data.resultCode === 0) {
                dispatch(followAC(userId));
            }
        });
};

export const unfollowTC = (userId) => (dispatch) => {
    usersAPI.unfollow(userId)
        .then(response => {
            if (response.data.resultCode === 0) {
                dispatch(unfollowAC(userId));
            }
        });
};

export default usersReducer;