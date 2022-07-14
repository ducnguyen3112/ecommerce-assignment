import React from 'react';
import SignIn from "../SignIn";
import Signup from "../SignUp";

function Login(props) {
    return (
        <div className={"login-container"}>
            <SignIn/>
            <Signup/>
        </div>
    );
}

export default Login;