import * as constants from "../constants/Product";
import * as axiosURL from "../constants/Axios";
import axios from "axios";

const productAction = {
    featuredProduct: () => async (dispatch) => {
        try {
            dispatch({type: constants.PRODUCT_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + "/products/featured-products",
            );
            dispatch({type: constants.PRODUCT_SUCCESS, payload: data.data.data});
        } catch (error) {
            dispatch({
                type: constants.PRODUCT_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    },
    shopProduct: (category) => async (dispatch) => {
        try {
            dispatch({type: constants.PRODUCT_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + "/products",
                {
                    params: {
                        category: category,
                    }
                }
            );
            dispatch({type: constants.PRODUCT_SUCCESS, payload: data.data.data});
        } catch (error) {
            dispatch({
                type: constants.PRODUCT_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    },

};

export default productAction;
