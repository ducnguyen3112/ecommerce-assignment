import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from "react-redux";

import action from '../../redux/actions/Product';
import ProductCard from "./ProductCard";

function ProductList() {
    const dispatch = useDispatch();
    const [products, setProducts] = useState([])

    const productList = useSelector((state) => state.product);
    const {loading, error, featuredProduct} = productList;

    useEffect(() => {
        dispatch(action.featuredProduct());
    }, []);
   console.log(products);
    return (
        <div>
            {/*{featuredProduct.data.map((item, index) => (*/}
            {/*    <ProductCard key={index} props={item}/>*/}
            {/*))}*/}
        </div>
    );
}

export default ProductList;