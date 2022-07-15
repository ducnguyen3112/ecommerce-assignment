import React, {useEffect} from 'react';
import {useDispatch, useSelector} from "react-redux";
import Accordion from "./Accordion";
import ProductList from "./ProductList";
import productAction from "../../redux/actions/Product";
import accordionAction from "../../redux/actions/Accordion";

function Shop() {
    const dispatch = useDispatch();

    const productList = useSelector((state) => state.product);
    const {productLoading, productError, products} = productList;

    useEffect(() => {
        dispatch(productAction.shopProduct());
    }, [dispatch]);


    const categoryList = useSelector((state) => state.category);
    const {categoryLoading, categoryError, categories} = categoryList;

    useEffect(() => {
        dispatch(accordionAction.getCategory());
    }, [dispatch]);
    console.log("categories " + categories);


    return (
        <div className="container section-body">

            <Accordion data={categories}
                       loading={categoryLoading}
                       error={categoryError}
            />

            <ProductList data={products}
                         loading={productLoading}
                         error={productError}/>
        </div>
    );
}

export default Shop;