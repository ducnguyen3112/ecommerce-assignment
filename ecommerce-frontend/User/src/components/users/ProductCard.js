import React from "react";

import {Spinner} from "react-bootstrap";
import {Link, useParams} from "react-router-dom";

function ProductCard(props) {

    return (
        <div className={'product-card'}>
            {props.loading ? (
                <Spinner
                    animation="border"
                    role="status"
                    style={{textAlign: "center"}}
                >
                    <span className="visually-hidden">Loading...</span>
                </Spinner>
            ) : (
                <Link to={'/product-detail/'+ props.data.id}>
                    <div className=" product-card" id={props.data.id} >
                        <div className="card product-height">
                            <img className="card-img-top" src={props.data.image} alt="..."/>
                            <div className="card-body p-4">
                                <div className="text-center">
                                    <h6 className="fw-bolder">{props.data.productName}</h6>
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
                </Link>
            )}
        </div>
    );
}
export default ProductCard;
