import axios from "axios";
import * as axiosURL from "../../../redux/constants/Axios";

class UserService {
    getUserInfo(id) {
        const url = axiosURL.AXIOS_HEROKU_URL + `/admin/users/${id}`
        const params = {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
            }
        }
        return axios.get(url, params)
    }

    changUserInfo(data, id) {
        const url = axiosURL.AXIOS_HEROKU_URL + `/admin/users/${id}`
        const params = {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
            }
        }
        return axios.put(url, data, params)
    }

    createNewUser(userPayload){
        const url = axiosURL.AXIOS_HEROKU_URL + `/admin/users`
        console.log(url)
        const params = {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
            }
        }
        return axios.post(url, userPayload, params)
    }
}

export default  new UserService()