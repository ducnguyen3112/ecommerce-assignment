import * as constants from "../constants/Auth"

const authReducer = (state = {}, action) => {
    switch (action.type) {
        case constants.USER_REGISTER_REQUEST:
            return { ...state, loading: true};
        case constants.USER_REGISTER_SUCCESS:
            return { loading: false, userInfo: action.payload };
        case constants.USER_REGISTER_FAIL:
            return { loading: false, error: action.payload };
        case constants.USER_LOGIN_REQUEST:
            return { ...state, loading: true};
        case constants.USER_LOGIN_SUCCESS:
            return { loading: false, userInfo: action.payload };
        case constants.USER_LOGIN_FAIL:
            return { loading: false, error: action.payload };
        case constants.USER_LOGOUT:
            return {};
        default:
            return state;
    }
};

export default authReducer;