import React from 'react';
import { useSelector } from "react-redux";

function Header() {
    const userLogin = useSelector((state) => state.auth)
    const  {error, loading, userInfo} = userLogin
    return (
        <header className="bg-dark py-5">
            <div className="container px-4 px-lg-5 my-5">
                <div className="text-center text-white">
                    <h1 className="display-4 fw-bolder">Shop in style</h1>
                    <p className="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
    );
}
export default Header;