import {
    CATEGORY_GET_FAIL,
    CATEGORY_GET_REQUEST,
    CATEGORY_GET_SUCCESS
} from "../constants/Category";
import axios from "axios";
import * as axiosURL from "../constants/Axios";

const accordionAction = {
    getCategory: () => async (dispatch) => {
        try {
            dispatch({type: CATEGORY_GET_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + "/categories"
            );
            dispatch({type: CATEGORY_GET_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type: CATEGORY_GET_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    }
}
export default accordionAction;