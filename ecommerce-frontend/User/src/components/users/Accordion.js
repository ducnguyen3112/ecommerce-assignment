import React, {useEffect, useState} from 'react';
import axios from "axios";

function Accordion(props) {
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const getCategories= async  () =>{
            const response =await axios.get("https://ecommerce-nashtech-assignment.herokuapp.com/api/categories")
            console.log(response)
            setCategories(response.data)
        }
        getCategories()
    },[])
    const data = categories.map((item , index) => {
        // console.log(item)
        return (
            <div className="accordion-item accordion" key={index}>
                <h2 className="accordion-header" id="headingOne">
                    <button className="accordion-button" type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        {item.categoryName}
                    </button>
                </h2>
                <div id="collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne"
                     data-bs-parent="#accordionExample">
                    <div className="accordion-body">
                        {
                            item.childCategories.map((child, index) => {
                                return (
                                    <div key={index}>
                                        {
                                            child.categoryName
                                        }
                                    </div>
                                )
                            })
                        }
                    </div>
                </div>
            </div>
        )
    })
    return (
        <div className="accordion col-lg-3" id="accordionExample" >
            {data}

        </div>

    );
}

export default Accordion;