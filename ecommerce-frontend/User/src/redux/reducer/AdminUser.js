import {
    USER_DELETE_FAIL,
    USER_DELETE_REQUEST, USER_DELETE_SUCCESS,
    USER_GET_FAIL,
    USER_GET_REQUEST,
    USER_GET_SUCCESS
} from "../constants/AdminUsers";


const adminUserReducer = (state = {adminUsers: []}, action) => {
    switch (action.type) {
        case USER_GET_REQUEST:
            return {...state, loading: true};
        case USER_GET_SUCCESS:
            return {loading: false, adminUsers: action.payload};
        case USER_GET_FAIL:
            return {loading: false, error: action.payload};
        case USER_DELETE_REQUEST:
            return {...state, loading: true};
        case USER_DELETE_SUCCESS:
            return {loading: false, adminUsers: action.payload};
        case USER_DELETE_FAIL:
            return {loading: false, error: action.payload};
        default:
            return state;
    }
};

export default adminUserReducer;