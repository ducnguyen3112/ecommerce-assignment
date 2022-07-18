import {
    RATING_LIST_FAIL,
    RATING_LIST_REQUEST,
    RATING_LIST_SUCCESS
} from "../constants/RatingList";
import axios from "axios";
import * as axiosURL from "../constants/Axios";


const ratingListAction = {
    getRatingList: (productId) => async (dispatch) => {
        try {
            dispatch({type: RATING_LIST_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + `/products/${productId}` + `/rating`,
            );
            dispatch({type: RATING_LIST_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type: RATING_LIST_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    },
};
export default ratingListAction;