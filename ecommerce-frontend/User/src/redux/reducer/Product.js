import * as constants from "../constants/Product"

const productReducer = (state = {featuredProduct: []}, action) => {
    switch (action.type) {
        case constants.PRODUCT_REQUEST:
            return { loading: true, featuredProduct: []};
        case constants.PRODUCT_SUCCESS:
            return { loading: false, featuredProduct: action.payload };
        case constants.PRODUCT_FAIL:
            return { loading: false, error: action.payload };
        default:
            return state;
    }
};

export default productReducer;