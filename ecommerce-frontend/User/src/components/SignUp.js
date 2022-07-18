import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";
import {useLocation, useNavigate} from 'react-router-dom'

import action from './../redux/actions/Auth';
import selector from './../redux/selector/Auth';

import Alert from 'react-bootstrap/Alert';
import Spinner from 'react-bootstrap/Spinner';

function Signup() {
    let navigate = useNavigate();
    let location = useLocation();
    const dispatch = useDispatch();

    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [password, setPassword] = useState("");

    const userRegister = useSelector(selector.userRegister)
    const {error, loading, userInfo} = userRegister;

    const redirect = location.search ? location.search.split("=")[1] : "/";

    useEffect(() => {
        if (localStorage.getItem("userInfo")) {
            navigate(redirect);
        }
    }, [userInfo, navigate, redirect])

    const submitHandler = (e) => {
        e.preventDefault();
        dispatch(action.register(firstName, lastName, email, phoneNumber, password));
    };
    return (
        <form onSubmit={submitHandler} className={'login-form'}>
            <h3>Sign Up</h3>
            {error &&
                <Alert variant={'danger'}>
                    {error}
                </Alert>
            }
            {loading &&
                <Spinner animation="border" role="status">
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            }
            <div className="mb-3">
                <label>First name</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="First name"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                />
            </div>
            <div className="mb-3">
                <label>Last name</label>
                <input
                    type="text"
                    className="form-control"
                    placeholder="Last name"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                />
            </div>
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
                <label for="phone">Enter your phone number:</label>
                <input
                    type="tel"
                    id="phone"
                    className="form-control"
                    placeholder="Enter phone number"
                    pattern="[0]{1}[0-9]{9}"
                    value={phoneNumber}
                    onChange={(e) => setPhoneNumber(e.target.value)}/>
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
                    Sign Up
                </button>
            </div>
        </form>
    );
}

export default Signup;