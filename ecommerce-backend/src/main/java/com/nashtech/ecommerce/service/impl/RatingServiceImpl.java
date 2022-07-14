package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;
import com.nashtech.ecommerce.entity.Rating;
import com.nashtech.ecommerce.entity.RatingId;
import com.nashtech.ecommerce.repository.RatingRepository;
import com.nashtech.ecommerce.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository ratingRepository;
    ModelMapper modelMapper;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
        super();
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseRatingDto addRating(RequestRatingDto requestRatingDto, Long productId) {
        Rating rating = new Rating();
        RatingId ratingId = new RatingId(requestRatingDto.getUserId(), productId);
        rating.setScores(requestRatingDto.getScores());
        rating.setComment(requestRatingDto.getComment());
        rating.setRatingId(ratingId);
        rating = ratingRepository.save(rating);
        return modelMapper.map(rating, ResponseRatingDto.class);
    }
}
