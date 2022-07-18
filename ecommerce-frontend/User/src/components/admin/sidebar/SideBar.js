import React from 'react';

const SideBar = () => {
    return (
        <div>
            <nav id="sidebarMenu"
                 className="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div className="position-sticky pt-3">
                    <h1 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb- text-muted text-uppercase">
                        <span>Management</span>
                        <a className="link-secondary" href="#"
                           aria-label="Add a new report">
                            <span data-feather="plus-circle"
                                  className="align-text-bottom"></span>
                        </a>
                    </h1>
                    <hr/>
                    <ul className="nav flex-column mb-2">
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                <span data-feather="file-text"
                                      className="align-text-bottom menu-active">Users</span>

                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                <span data-feather="file-text"
                                      className="align-text-bottom">Products</span>
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                <span data-feather="file-text"
                                      className="align-text-bottom">Categories</span>

                            </a>
                        </li>

                    </ul>
                </div>
                <div className="position-sticky pt-3">
                    <h1 className="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted text-uppercase">
                        <span>Action</span>
                        <a className="link-secondary" href="#"
                           aria-label="Add a new report">
                            <span data-feather="plus-circle"
                                  className="align-text-bottom"></span>
                        </a>
                    </h1>
                    <hr/>
                    <ul className="nav flex-column mb-2">
                        <li className="nav-item">
                            <a className="nav-link" href="#">
                                <span data-feather="file-text"
                                      className="align-text-bottom">Sign out</span>

                            </a>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>
    );
};

export default SideBar;
