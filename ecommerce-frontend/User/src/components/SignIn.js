import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {Link, useLocation, useNavigate} from 'react-router-dom'

import action from './../redux/actions/Auth';
import selector from './../redux/selector/Auth';

import Alert from 'react-bootstrap/Alert';
import Spinner from 'react-bootstrap/Spinner';

const SignIn = () => {
    let navigate = useNavigate();
    let location = useLocation();
    const dispatch = useDispatch();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const userLogin = useSelector(selector.userLogin)

    const {error, loading, userInfo} = userLogin;

    const redirect = location.search ? location.search.split("=")[1] : "/";

    useEffect(() => {
        if (localStorage.getItem("userInfo")) {
            navigate(redirect);
        }
    }, [userInfo, navigate, redirect])

    const submitHandler = (e) => {
        e.preventDefault();
        dispatch(action.login(email, password));
    };
    return (
        <form onSubmit={submitHandler} className={'login-form'}>
            <h3>Sign In</h3>
            {error &&
                <Alert variant={'danger'}>
                    {error}
                </Alert>
            }
            {loading &&
                <Spinner animation="border" role="status" style={{textAlign: "center"}}>
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            }
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
    );
}

export default SignIn;