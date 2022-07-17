
import axios from "axios";
import * as axiosURL from "../constants/Axios";
import {PRODUCT_DETAIL_FAIL, PRODUCT_DETAIL_REQUEST, PRODUCT_DETAIL_SUCCESS} from "../constants/ProductDetail";

const productDetailAction = {
    productDetail: (id) => async (dispatch) => {
        try {
            dispatch({type: PRODUCT_DETAIL_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + `/products/${id}`
            );
            dispatch({type: PRODUCT_DETAIL_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type: PRODUCT_DETAIL_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    },

};

export default productDetailAction;
