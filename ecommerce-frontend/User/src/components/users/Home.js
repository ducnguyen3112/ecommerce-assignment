import React, {useEffect} from 'react';
import {useDispatch, useSelector} from "react-redux";
import action from "../../redux/actions/Product";
import Header from "./Header";
import ProductList from "./ProductList";
import Navbar from "./Navbar";
import Footer from "./Footer";

function Home() {
    const dispatch = useDispatch();
    const productList = useSelector((state) => state.product);
    const {loading, error, products} = productList;
    const center = 'product-list-center';

    useEffect(() => {
        dispatch(action.featuredProduct());
    }, [dispatch]);
    console.log(products);
    return (
        <>
            <Navbar/>
            <Header/>
            <ProductList data={products}
                         loading={loading}
                         error={error}
                         center={center}
            />
            <Footer/>
        </>
    );
}

export default Home;