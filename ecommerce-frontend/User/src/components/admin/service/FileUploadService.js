import React from "react";
import axios from "axios";
import {AXIOS_HEROKU_URL} from "../../../redux/constants/Axios";

class FileUploadService {


    fileUploadSetup(e) {
        const IMAGE_UPLOAD_URL =
         AXIOS_HEROKU_URL+"/admin/cloudinary";
        const params = {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('Token').replaceAll('"','')
            }
        }

        //validate file
        let files = e.target.files[0];
        e.preventDefault();

        const formData = new FormData();
        formData.append('file', files);
        formData.append('fileName', files.name);
        
        return axios.post(IMAGE_UPLOAD_URL, formData,params);
    }
}

export default new FileUploadService();