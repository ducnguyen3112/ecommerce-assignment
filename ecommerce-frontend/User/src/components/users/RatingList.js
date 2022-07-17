import React, {useEffect} from 'react';
import ratingListAction from "../../redux/actions/RatingList";
import Rating from "./Rating";
import {useDispatch, useSelector} from "react-redux";

const RatingList = (props) => {
    const {listRating,setListRating} = props
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(ratingListAction.getRatingList(props.productId));
    }, [dispatch]);
    const ratingListSelector = useSelector((state) => state.ratingList);
    const {ratingListLoading, ratingListError, ratingList} = ratingListSelector;

    useEffect(() => {
        setListRating(ratingList)
    }, [ratingList]);
    return (
        <div className={"w-50"}>
            {listRating.map((item)=>(
            <Rating data={item} />
            ))}
        </div>
    );
};

export default RatingList;
