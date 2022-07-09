import React, { useEffect, useState } from 'react';
import axios from "axios";
function ProductCard(props) {
    const [products, setProducts] = useState([])

    useEffect(() => {
        const getProduct = async () => {
            const response = await axios.get("https://ecommerce-nashtech-assignment.herokuapp.com/api/products",
                { 'headers': { 'Authorization': "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkdWNpbm94MjAwMEBnbWFpbC5jb20iLCJpYXQiOjE2NTczNzA4MzcsImV4cCI6MTY1NzQ1NzIzN30.eRaGP7oxwYL9X5lxRjvEAdlXFKf3dqGDhLys1jJl-7c" } })
            setProducts(response.data.data)
        }
        getProduct()
    }, [])
    return (
        <div className="row gx-lg-5 row-cols-4  row-cols-xl-4 ">
            {products.map((product) => {
                return <div className=" product-card ">
                    <div className="card h-100">
                        <img className="card-img-top" src={product.image} alt="..." />
                        <div className="card-body p-4">
                            <div className="text-center">
                                <h6 className="fw-bolder">{product.productName}</h6>
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
                            <div className="text-center"><a className="btn btn-outline-dark mt-auto" href="#">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </div>
            })}
        </div>
    );
}

export default ProductCard;