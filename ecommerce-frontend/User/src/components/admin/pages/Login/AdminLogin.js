import React, {useEffect, useState} from 'react';
import Alert from "react-bootstrap/Alert";
import {AXIOS_HEROKU_URL} from "../../../../redux/constants/Axios";
import axios from "axios";
import {useLocation, useNavigate} from "react-router-dom";

const AdminLogin = () => {

    let navigate = useNavigate();
    let location = useLocation();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState([]);
    const [userInfo, setUserInfo] = useState([]);

    const redirect = location.search ? location.search.split("=")[1] : "/admin";

    const loginAction = (email,password) => {
        axios.post(AXIOS_HEROKU_URL+'/auth/signin', {
            email: email,
            password: password
        })
            .then(function (response) {
                console.log(response.data)
                response.data.roles.map((item)=>{
                    if (item.authority=="ROLE_ADMIN"){
                        setUserInfo(response.data);
                        localStorage.setItem("userInfo", JSON.stringify(response.data))
                        localStorage.setItem("Token", response.data.token);
                    }
                })
            })
            .catch(function (error) {
                console.log(error.response)
                setError(error.response.data)
            });
    }
    useEffect(() => {
        if (localStorage.getItem("userInfo")) {
            navigate(redirect);
        }
    }, [userInfo])
    const submitHandler = (e) => {
        e.preventDefault();
        console.log(1)
        loginAction(email,password);
    };
    return (
        <div className={'d-flex justify-content-center align-items-center login-admin'}>
            <form onSubmit={submitHandler} className={'login-form '}>
                <h3>Sign In</h3>
                {!Array.isArray(error)?(
                    <Alert variant={'danger'}>
                        {error.message}
                    </Alert>
                ):("")}
                <div className="mb-3">
                    <label>Email address</label>
                    <input
                        type="email"
                        className="form-control"
                        placeholder="Enter email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="mb-3">
                    <label>Password</label>
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Enter password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <div className="d-grid">
                    <button type="submit" className="btn btn-primary">
                        Submit
                    </button>
                </div>
            </form>
        </div>
    );
}

export default AdminLogin;