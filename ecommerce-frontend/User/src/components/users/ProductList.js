import React from 'react';

import Alert from 'react-bootstrap/Alert';
import ProductCard from "./ProductCard";

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
                        key={item.id}
                        isLoading={props.loading}
                        productName={item.productName}
                        image={item.image}
                    />
                ))}
                </div>
            )}
        </div>
    );
}

export default ProductList;