import React, {useEffect, useState} from 'react';
import axios from "axios";

function Accordion(props) {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const getCategories= async  () =>{
            const response =await axios.get("https://ecommerce-nashtech-assignment.herokuapp.com/api/categories",
                { 'headers': { 'Authorization': "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkdWNpbm94MjAwMEBnbWFpbC5jb20iLCJpYXQiOjE2NTcyNTg4MDUsImV4cCI6MTY1NzM0NTIwNX0.Ya_JeuMDNVRRll_xC1WpMugvZQy9D8dKwod81mANTlM" } })
            console.log(response)
            setCategories(response.data)
        }
        getCategories()
    },[])
    return (
        <div className="accordion col-lg-3" id="accordionExample" >
            <div className="accordion-item">
                <h2 className="accordion-header" id="headingOne">
                    <button className="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Accordion Item #1
                    </button>
                </h2>
                <div id="collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne"
                     data-bs-parent="#accordionExample">
                    <div className="accordion-body">
                        Category
                    </div>
                </div>
            </div>
            <div className="accordion-item">
                <h2 className="accordion-header" id="headingOne">
                    <button className="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Accordion Item #1
                    </button>
                </h2>
                <div id="collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne"
                     data-bs-parent="#accordionExample">
                    <div className="accordion-body">
                        Category
                    </div>
                </div>
            </div>
        </div>

    );
}

export default Accordion;