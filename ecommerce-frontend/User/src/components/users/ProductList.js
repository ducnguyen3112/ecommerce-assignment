import React from 'react';
import Alert from 'react-bootstrap/Alert';
import ProductCard from "./ProductCard";
import {useParams} from "react-router-dom";

function ProductList(props) {

    return (
        <div className={'container product-container'}>
            {props.error ? (
                <Alert variant={'danger'}>
                    {props.error}
                </Alert>
            ) : (
                <div className={props.center + ' product-list'}>{props.data.map((item) => (
                    <ProductCard
                        data={item}
                    />
                ))}
                </div>
            )}
        </div>
    );
}

export default ProductList;