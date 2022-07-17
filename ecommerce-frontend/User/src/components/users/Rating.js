import React from 'react';
import ReactStars from "react-rating-stars-component";

const Rating = (props) => {

    const handleRating = {
        size: 15,
        count: 5,
        value: props.data.scores,
        a11y: true,
        isHalf: true,
        emptyIcon: <i className="bi bi-star"></i>,
        halfIcon: <i className="bi bi-star-half"></i>,
        filledIcon: <i className="bi bi-star-fill"></i>,
        edit:false,
    };

    return (
        <div className="card mb-4">
            <div className="card-body">
                <div className="d-flex justify-content-between">
                    <div className="d-flex flex-row align-items-center">
                        <img src={props.data.avatar} alt="avatar" width="25"
                             height="25" className={"rounded-circle mt-1"}/>
                        <p className="small mb-0 ms-2">{props.data.fullName}</p>
                    </div>
                    <div className="d-flex flex-row align-items-center">
                        <i className="far fa-thumbs-up mx-2 fa-xs text-black" ></i>
                        <ReactStars  {...handleRating}/>
                        {console.log(handleRating.value)}
                    </div>
                </div>

                <div className={'comment-text'}>
                <span>{props.data.comment}</span></div>
            </div>
        </div>
    );
};

export default Rating;
