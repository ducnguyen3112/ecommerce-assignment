import React from "react";
import PropTypes from "prop-types";

import { Spinner } from "react-bootstrap";

function ProductCard(props) {
    return (
        <div >
            {props.isLoading ? (
                <Spinner
                    animation="border"
                    role="status"
                    style={{ textAlign: "center" }}
                >
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            ) : (
                <div >
                    <div className=" product-card">
                        <div className="card product-height">
                            <img className="card-img-top" src={props.image} alt="..." />
                            <div className="card-body p-4">
                                <div className="text-center">
                                    <h6 className="fw-bolder">{props.productName}</h6>
                                    <div className="d-flex justify-content-center small text-warning mb-2">
                                        <div className="bi bi-star-fill"></div>
                                        <div className="bi-star-fill"></div>
                                        <div className="bi-star-fill"></div>
                                        <div className="bi-star-fill"></div>
                                        <div className="bi-star-fill"></div>
                                    </div>
                                </div>
                            </div>
                            <div className="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div className="text-center">
                                    <a className="btn btn-outline-dark mt-auto" href="#!">
                                        Add to cart
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}

ProductCard.propTypes = {
    isLoading: PropTypes.bool.isRequired,
    image: PropTypes.string.isRequired,
    productName: PropTypes.string.isRequired,
};

export default ProductCard;
