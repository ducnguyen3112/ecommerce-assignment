import {
    USER_GET1_FAIL,
    USER_GET1_REQUEST,
    USER_GET1_SUCCESS
} from "../constants/AdminUsers";


const OneAdminUserReducer = (state = {oneAdminUser: []}, action) => {
    switch (action.type) {
        case USER_GET1_REQUEST:
            return {...state, loading: true};
        case USER_GET1_SUCCESS:
            return {loading: false, oneAdminUser: action.payload};
        case USER_GET1_FAIL:
            return {loading: false, error: action.payload};
        default:
            return state;
    }
};

export default OneAdminUserReducer;