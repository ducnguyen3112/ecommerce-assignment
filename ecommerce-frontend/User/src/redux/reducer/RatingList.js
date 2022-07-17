import {RATING_LIST_FAIL, RATING_LIST_REQUEST, RATING_LIST_SUCCESS} from "../constants/RatingList";

const ratingListReducer = (state = {ratingList: []}, action) => {
    switch (action.type) {
        case RATING_LIST_REQUEST:
            return { ...state, ratingListLoading: true};
        case RATING_LIST_SUCCESS:
            return { ratingListLoading: false, ratingList: action.payload };
        case RATING_LIST_FAIL:
            return { ratingListLoading: false, ratingListError: action.payload };
        default:
            return state;
    }
};

export default ratingListReducer;