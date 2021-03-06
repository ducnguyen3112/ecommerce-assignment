package com.nashtech.ecommerce.dto.response;

import com.nashtech.ecommerce.entity.RatingId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRatingDto {

    private RatingId ratingId;
    private int scores;
    private String comment;
}
