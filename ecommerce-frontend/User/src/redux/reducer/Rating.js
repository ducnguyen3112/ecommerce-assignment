import {RATING_FAIL, RATING_REQUEST, RATING_SUCCESS} from "../constants/Rating";

const ratingReducer = (state = {rating: []}, action) => {
    switch (action.type) {
        case RATING_REQUEST:
            return {...state, ratingLoading: true};
        case RATING_SUCCESS:
            return {ratingLoading: false, rating: action.payload};
        case RATING_FAIL:
            return {ratingLoading: false, ratingError: action.payload};
        default:
            return state;
    }
};

export default ratingReducer;