import React, {useState} from 'react';
import ReactStars from "react-rating-stars-component";
import {useDispatch, useSelector} from "react-redux";
import ratingAction from "../../redux/actions/Rating";
import selector from "../../redux/selector/Auth";


const SendRating = (props) => {
    const {listRating, setListRating} = props
    const userLogin = useSelector(selector.userLogin);
    const userRating = useSelector((state) => state.ratingList);
    let scores;
    const {userInfo} = userLogin;
    let comment = "";
    let disabled;
    const dispatch = useDispatch();
    const [temp, setTemp] = useState(1)
    const handleRating = {
        size: 20,
        count: 5,
        a11y: true,
        isHalf: true,
        emptyIcon: <i className="bi bi-star"></i>,
        halfIcon: <i className="bi bi-star-half"></i>,
        filledIcon: <i className="bi bi-star-fill"></i>,
        onChange: newValue => {
            scores = newValue;
        }
    };
    const isCommented = () => {
        listRating.map((item) => {
            if (userInfo.data != undefined) {
                if (item.userId == userInfo.data.id) {
                    disabled = true;
                } else {
                    disabled = false;
                }
            }
        })
    }
    isCommented();


    const handleSendClick = () => {
        // e.preventDefault();
        console.log(scores)
        setListRating([{
            userId: userInfo.data.id,
            avatar: "https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?w=2000",
            comment: comment,
            fullName: "Duc Nguyen",
            scores: scores,
        }, ...listRating])
        dispatch(ratingAction.addRating(props.productId, userInfo.data.id, comment, scores));
    }
    return (
        <div>
            {
                userInfo.data != undefined ? (
                    <div className="card rating-card ">
                        <div className="row">
                            <div className="col-1">
                                <img src={userInfo.data.image} width="50"
                                     className="rounded-circle mt-1"/>
                            </div>
                            <div className="col-11">
                                <div className="comment-box ml-2">
                                    <h5>{userInfo.data.name}</h5>
                                    <div className="rating">
                                        <div className="App">
                                            <ReactStars {...handleRating} />
                                        </div>
                                    </div>
                                    <div className="comment-area">
                                    <textarea onChange={(e) => comment = e.target.value}
                                              className="form-control"
                                              placeholder="what is your view?"
                                              rows="2"></textarea>
                                    </div>
                                    <div className="comment-btn mt-2">
                                        <div>
                                            <div>
                                                <div>
                                                    <button
                                                        onClick={() => handleSendClick()}
                                                        className={`${disabled === true ? "disabled btn btn-success send btn-sm" : " btn btn-success send btn-sm"}`}>Send <i
                                                        className="fa fa-long-arrow-right ml-1"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                ) : (
                    <div></div>
                )
            }
        </div>
    );
};

export default SendRating;
