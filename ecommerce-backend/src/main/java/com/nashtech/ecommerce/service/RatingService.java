package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseListRating;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;

import java.util.List;

public interface RatingService {

    ResponseRatingDto addRating(RequestRatingDto requestRatingDto, Long productId);

    List<ResponseListRating> getRatingsOfProduct(Long productId);
}
