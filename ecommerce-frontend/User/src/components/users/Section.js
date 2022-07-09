import React from 'react';
import ProductCard from "./ProductCard";
import Pagination from "../Pagination";
import Accordion from "./Accordion";

function Section(props) {
    return (
        <>
            <div className="container section-body px-4 px-lg-5 mt-5 ">
                <Accordion className='accordion-body' />
                <ProductCard />
            </div>
            <Pagination />
        </>
    );
}

export default Section;