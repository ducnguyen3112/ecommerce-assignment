import {applyMiddleware, combineReducers, createStore} from "redux";
import {composeWithDevTools} from "redux-devtools-extension";
import thunk from "redux-thunk";

import authReducer from "./reducer/Auth";
import productReducer from "./reducer/Product";
import categoryReducer from "./reducer/Category";
import productDetailReducer from "./reducer/ProductDetail";
import ratingReducer from "./reducer/Rating";
import ratingListReducer from "./reducer/RatingList";

const reducer = combineReducers({
    auth: authReducer,
    product: productReducer,
    category: categoryReducer,
    productDetail:productDetailReducer,
    rating: ratingReducer,
    ratingList:ratingListReducer,
});

const userInfoFromLocalStorage = localStorage.getItem("userInfo")
    ? JSON.parse(localStorage.getItem("userInfo"))
    : [];

const initialState = {
    auth: {userInfo: userInfoFromLocalStorage}
};

const middleware = [thunk]

const store = createStore(
    reducer,
    initialState,
    composeWithDevTools(applyMiddleware(...middleware))
);

export default store