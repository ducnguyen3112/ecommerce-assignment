import axios from "axios";
import * as axiosURL from "../constants/Axios";
import {
    USER_DELETE_FAIL,
    USER_DELETE_REQUEST,
    USER_DELETE_SUCCESS,
    USER_GET1_FAIL,
    USER_GET1_REQUEST,
    USER_GET1_SUCCESS,
    USER_GET_FAIL,
    USER_GET_REQUEST,
    USER_GET_SUCCESS
} from "../constants/AdminUsers";

const adminUserAction = {
    getAdminUser: () => async (dispatch) => {
        try {
            dispatch({type: USER_GET_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + "/admin/users",{
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
                    }}

            );
            dispatch({type: USER_GET_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type:USER_GET_FAIL ,
                payload: error.message,
            });
            console.log(error.message)
        }
    },
    deleteAdminUser:(id) => async (dispatch) =>{
        try {
            dispatch({type: USER_DELETE_REQUEST});
            const data = await axios.delete(
                axiosURL.AXIOS_HEROKU_URL + `/admin/users/${id}`,{
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
                    }}
            );
            dispatch({type: USER_DELETE_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type:USER_DELETE_FAIL ,
                payload: error.message,
            });
            console.log(error.message)
        }
    },
    get1AdminUser:(id) => async (dispatch) =>{
        try {
            dispatch({type: USER_GET1_REQUEST});
            const data = await axios.get(
                axiosURL.AXIOS_HEROKU_URL + `/admin/users/${id}`,{
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
                    }}
            );
            dispatch({type: USER_GET1_SUCCESS, payload: data.data});
        } catch (error) {
            dispatch({
                type:USER_GET1_FAIL ,
                payload: error.message,
            });
            console.log(error.message)
        }
    },

}
export default adminUserAction;