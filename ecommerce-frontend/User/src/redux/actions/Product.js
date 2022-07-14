import * as constants from "../constants/Product";
import  axios  from "axios";

const action = {
    featuredProduct: () => async (dispatch) => {
        try {
            dispatch({ type: constants.PRODUCT_REQUEST });
            const data  = await axios.get(
                "https://ecommerce-nashtech-assignment.herokuapp.com/api/products/featured-products",
            );
             dispatch({ type: constants.PRODUCT_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type: constants.PRODUCT_FAIL,
                payload: error.message,
            });
            console.log(error.message)
        }
    }
};

export default action;
