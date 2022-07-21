import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import productDetailAction from "../../redux/actions/ProductDetail";
import NumberFormat from "react-number-format";
import SendRating from "./SendRating";
import RatingList from "./RatingList";
import Footer from "./Footer";
import Navbar from "./Navbar";

function ProductDetail() {
    const dispatch = useDispatch();

    const {id} = useParams();
    const productDetailSelector = useSelector((state) => state.productDetail);
    const {
        productDetailLoading,
        productDetailError,
        productDetail
    } = productDetailSelector;

    useEffect(() => {
        dispatch(productDetailAction.productDetail(id));
    }, [dispatch]);

    const [listRating, setListRating] = useState([])
    return (
        <>
            <Navbar/>
        <div className={'container container-comment'}>
            <section>
                <div className=" px-4 px-lg-5 my-5">
                    <div className="row gx-4 gx-lg-5 align-items-center">
                        <div className="col-md-6"><img
                            className="card-img-top mb-5 mb-md-0"
                            src={productDetail.image}
                            alt="..."/></div>
                        <div className="col-md-6">
                            <h3 className=" fw-bolder">{productDetail.productName}</h3>
                            <div className="fs-5 mb-5">
                                <br/>
                                <NumberFormat value={productDetail.price}
                                              displayType={'text'}
                                              thousandSeparator={true} prefix={'VND '}/>
                            </div>
                            <p className="lead">{productDetail.description}</p>
                            <div className="d-flex">
                                <input className="form-control text-center me-3"
                                       id="inputQuantity" type="num" value="1"
                                />
                                <button className="btn btn-outline-dark flex-shrink-0"
                                        type="button">
                                    <i className="bi-cart-fill me-1"></i>
                                    Add to cart
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <SendRating productId={id} listRating={listRating}
                        setListRating={setListRating}/>
            <RatingList productId={id} listRating={listRating}
                        setListRating={setListRating}/>
        </div>
            <Footer/>
        </>


    );
}

export default ProductDetail;