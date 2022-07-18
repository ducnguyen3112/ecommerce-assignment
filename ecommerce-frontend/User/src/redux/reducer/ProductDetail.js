import {
    PRODUCT_DETAIL_FAIL,
    PRODUCT_DETAIL_REQUEST,
    PRODUCT_DETAIL_SUCCESS
} from "../constants/ProductDetail";


const productDetailReducer = (state = {productDetail: []}, action) => {
    switch (action.type) {
        case PRODUCT_DETAIL_REQUEST:
            return {...state, productDetailLoading: true};
        case PRODUCT_DETAIL_SUCCESS:
            return {productDetailLoading: false, productDetail: action.payload};
        case PRODUCT_DETAIL_FAIL:
            return {productDetailLoading: false, productDetailError: action.payload};
        default:
            return state;
    }
};

export default productDetailReducer;