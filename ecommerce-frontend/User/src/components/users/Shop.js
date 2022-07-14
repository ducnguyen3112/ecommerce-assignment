import React from 'react';
import ProductCard from "./ProductCard";
import Accordion from "./Accordion";

function Shop(props) {
    return (
        <div className="container section-body">
            <Accordion />
            <ProductCard />
        </div>
    );
}

export default Shop;