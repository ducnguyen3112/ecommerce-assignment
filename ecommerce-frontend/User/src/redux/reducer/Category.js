import {
    CATEGORY_GET_FAIL,
    CATEGORY_GET_REQUEST,
    CATEGORY_GET_SUCCESS
} from "../constants/Category";


const categoryReducer = (state = {categories: []}, action) => {
    switch (action.type) {
        case CATEGORY_GET_REQUEST:
            return {...state, loading: true};
        case CATEGORY_GET_SUCCESS:
            return {loading: false, categories: action.payload};
        case CATEGORY_GET_FAIL:
            return {loading: false, error: action.payload};
        default:
            return state;
    }
};

export default categoryReducer;