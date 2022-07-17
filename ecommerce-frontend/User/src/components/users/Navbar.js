import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {Link} from "react-router-dom";

import action from "../../redux/actions/Auth";
import selector from './../../redux/selector/Auth';

const Navbar = () => {
    const dispatch = useDispatch();

    const userLogin = useSelector(selector.userLogin);
    const {userInfo} = userLogin;

    const logoutHandler = () => {
        dispatch(action.logout());
    };

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light nav-bar">
            <div className="container px-4 px-lg-5">
                <Link className="navbar-brand" to="/">
                    2PM
                </Link>
                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li className="nav-item">
                            <Link className="nav-link active" aria-current="page" to="/">
                                Home
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/shop">
                                Shop
                            </Link>
                        </li>
                    </ul>
                    <form className="d-flex">
                        <div
                            className="collapse navbar-collapse"
                            id="navbarSupportedContent"
                        >
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                                {localStorage.getItem("userInfo") ? (
                                    <li className="nav-item dropdown">
                                        <a
                                            className="nav-link dropdown-toggle"
                                            id="navbarDropdown"
                                            href="#!"
                                            role="button"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                        >
                                            Hello {userInfo.data.name}
                                        </a>
                                        <ul
                                            className="dropdown-menu"
                                            aria-labelledby="navbarDropdown"
                                        >
                                            <li>
                                                <Link className="dropdown-item" to="/profile">
                                                    Profile
                                                </Link>
                                            </li>
                                            <li>
                                                <hr className="dropdown-divider"/>
                                            </li>
                                            <li>
                                                <Link
                                                    className="dropdown-item"
                                                    to="#"
                                                    onClick={logoutHandler}
                                                >
                                                    Logout
                                                </Link>
                                            </li>
                                        </ul>
                                    </li>
                                ) : (
                                    <li className="nav-item dropdown">
                                        <a
                                            className="nav-link dropdown-toggle"
                                            id="navbarDropdown"
                                            href="#!"
                                            role="button"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                        >
                                            <i className="bi-person-fill me-1"></i>
                                        </a>
                                        <ul
                                            className="dropdown-menu"
                                            aria-labelledby="navbarDropdown"
                                        >
                                            <li>
                                                <Link className="dropdown-item" to="/login">
                                                    Login/Signup
                                                </Link>
                                            </li>
                                        </ul>
                                    </li>
                                )}
                                <li className="nav-item" style={{paddingLeft: 12}}>
                                    <button className="btn btn-outline-dark" type="submit">
                                        <i className="bi-cart-fill me-1"></i>
                                        Cart
                                        <span className="badge bg-dark text-white ms-1 rounded-pill">
                                            0
                                        </span>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
    );
};
export default Navbar;