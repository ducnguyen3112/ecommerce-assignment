import * as constants from "../constants/Product"

const productReducer = (state = {products: []}, action) => {
    switch (action.type) {
        case constants.PRODUCT_REQUEST:
            return { loading: true, products: []};
        case constants.PRODUCT_SUCCESS:
            return { loading: false, products: action.payload };
        case constants.PRODUCT_FAIL:
            return { loading: false, error: action.payload };
        default:
            return state;
    }
};

export default productReducer;