import {RATING_FAIL, RATING_REQUEST} from "../constants/Rating";
import axios from "axios";
import * as axiosURL from "../constants/Axios";


const ratingAction = {
    addRating: (productId, user_id, comment, scores) => async (dispatch) => {
        try {
            dispatch({type: RATING_REQUEST});
            await axios.post(
                axiosURL.AXIOS_HEROKU_URL + `/products/${productId}/rating`,
                {user_id, comment, scores},
            );
            console.log(axiosURL.AXIOS_HEROKU_URL + `${productId}/rating`)
        } catch (error) {
            dispatch({
                type: RATING_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    },
};
export default ratingAction;