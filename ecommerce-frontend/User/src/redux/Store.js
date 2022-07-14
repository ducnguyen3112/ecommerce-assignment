import { combineReducers, createStore, applyMiddleware } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import thunk from "redux-thunk";

import  authReducer  from "./reducer/Auth";
import productReducer from "./reducer/Product";

const reducer = combineReducers ({
    auth: authReducer,
    product: productReducer,
});

const userInfoFromLocalStorage = localStorage.getItem("userInfo")
    ? JSON.parse(localStorage.getItem("userInfo"))
    : [];

const initialState = {
    auth: {userInfo:userInfoFromLocalStorage}
};

const middleware = [thunk]

const store = createStore (
    reducer,
    initialState,
    composeWithDevTools(applyMiddleware(...middleware))
);

export default store