import React, {useEffect} from 'react';
import {useDispatch, useSelector} from "react-redux";
import Accordion from "./Accordion";
import action from "../../redux/actions/Product";
import ProductList from "./ProductList";

function Shop() {
    const dispatch = useDispatch();

    const productList = useSelector((state) => state.product);
    const {loading, error, products} = productList;

    useEffect(() => {
        dispatch(action.allProduct());
    }, [dispatch]);
    console.log(products);
    return (
        <div className="container section-body">
            <Accordion/>
            <ProductList data={products}
                         loading={loading}
                         error={error}/>
        </div>
    );
}
export default Shop;