import React from 'react';
import SignIn from "../SignIn";
import Signup from "../SignUp";
import Navbar from "./Navbar";
import Footer from "./Footer";

function Login(props) {
    return (
        <>
            <Navbar/>

            <div className={"login-container"}>
                <SignIn/>
                <Signup/>
            </div>
            <Footer/>
        </>
    );
}

export default Login;