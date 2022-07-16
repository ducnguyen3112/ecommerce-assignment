import React from 'react';
import Alert from "react-bootstrap/Alert";
import {Link} from "react-router-dom";

function Accordion(props) {
    const sendCategoryID=(id)=>{
        props.parentCallback(id);
    }

    function handleCategoryClick(id) {
        sendCategoryID(id)
    }

    return (
        <>
            {props.error ? (
                <Alert variant={'danger'}>
                    {props.error}
                </Alert>
            ) : (
                <div className="accordion col-lg-3" id="accordionExample">{props.data.map((item) => (
                    <div className="accordion-item accordion">
                        <h2 className="accordion-header" id="headingOne">
                            <button className="accordion-button" type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                {item.categoryName}
                            </button>
                        </h2>
                        <div id="collapseOne" className="accordion-collapse collapse show" aria-labelledby="headingOne"
                             data-bs-parent="#accordionExample">
                            <div className="accordion-body">
                                {item.childCategories.map((item) => (

                                        <div to={item.categoryName.toLowerCase()} className={'category-style'} onClick={handleCategoryClick.bind(this,item.id)} id={item.id}>
                                            {item.categoryName}
                                        </div>
                                ))}
                            </div>

                        </div>
                    </div>
                ))}
                </div>
            )}
        </>

    );
}

export default Accordion;