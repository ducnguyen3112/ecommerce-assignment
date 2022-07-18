package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.dto.request.RequestRatingDto;
import com.nashtech.ecommerce.dto.response.ResponseListRating;
import com.nashtech.ecommerce.dto.response.ResponseRatingDto;
import com.nashtech.ecommerce.entity.Rating;
import com.nashtech.ecommerce.entity.RatingId;
import com.nashtech.ecommerce.entity.User;
import com.nashtech.ecommerce.exception.ResourceNotFoundException;
import com.nashtech.ecommerce.repository.RatingRepository;
import com.nashtech.ecommerce.repository.UserRepository;
import com.nashtech.ecommerce.service.RatingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    RatingRepository ratingRepository;
    ModelMapper modelMapper;

    UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository, ModelMapper modelMapper) {
        super();
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

    @Override
    public List<ResponseListRating> getRatingsOfProduct(Long productId) {
        List<ResponseListRating> responseList = new ArrayList<>();
        List<Rating> ratingList = ratingRepository.findRatingByRatingIdProductId(productId).orElseThrow(() ->
                new ResourceNotFoundException("can't find list rating of product with id: " + productId));
        ratingList.forEach(rating -> {
            ResponseListRating responseListRating = modelMapper.map(rating, ResponseListRating.class);
            User user = userRepository.findById(rating.getRatingId().getUserId()).get();
            responseListRating.setAvatar(user.getAvatar());
            responseListRating.setUserId(user.getId());
            responseListRating.setFullName(user.getFullName());
            responseList.add(responseListRating);
        });

        return responseList;
    }
}
