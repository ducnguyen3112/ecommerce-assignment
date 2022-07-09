package com.nashtech.ecommerce.service;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;

public interface RatingService {

    ResponseRatingDto addRating(RequestRatingDto requestRatingDto, Long productId);

}
