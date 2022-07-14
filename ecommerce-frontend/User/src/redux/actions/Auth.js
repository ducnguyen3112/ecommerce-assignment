import * as constants from "../constants/Auth";
import  axios  from "axios";

const action = {
  register: (firstName, lastName, email, phoneNumber, password) => async (dispatch) => {
    try {
      dispatch({ type: constants.USER_REGISTER_REQUEST });
      const data  = await axios.post(
        "https://ecommerce-nashtech-assignment.herokuapp.com/api/auth/signup",
        {firstName, lastName, email, phoneNumber, password},
      );
      // dispatch({ type: constants.USER_REGISTER_SUCCESS, payload: data});
      document.location.href = "/Sign-In"
    } catch (error) {
        dispatch({
            type: constants.USER_REGISTER_FAIL,
            payload: error.message,
        });
        console.log(error.message)
    }
  },
  login: (email, password) => async (dispatch) => {
    try {
      dispatch({ type: constants.USER_LOGIN_REQUEST });
      const config = {
        header: {
          "Content-Type": "application/json",
        },
      };
      const data  = await axios.post(
        "https://ecommerce-nashtech-assignment.herokuapp.com/api/auth/signin",
        {email, password},
        config
      );
      dispatch({ type: constants.USER_LOGIN_SUCCESS, payload: data });
      localStorage.setItem("userInfo", JSON.stringify(data));
    } catch (error) {
        dispatch({
            type: constants.USER_LOGIN_FAIL,
            payload: error.message,
        });
        console.log(error.message)
    }
  },
  logout: () => (dispatch) => {
    localStorage.removeItem("userInfo");
    dispatch({ type: constants.USER_LOGOUT });
    document.location.href = "/Sign-In"
  },
};

export default action;
