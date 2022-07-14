import React, {useEffect, useState} from 'react';
import axios from "axios";

function ProductCard(props) {

    return (
        <div className="container section-body ">
            <div className="row product-center">

                    return <div className=" product-card ">
                        <div className="card h-100">
                            <img className="card-img-top" src={props.image} alt="..."/>
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
                                <div className="text-center"><a className="btn btn-outline-dark mt-auto" href="#">Add to
                                    cart</a>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
    );
}

export default ProductCard;