package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.Rating;
import com.nashtech.ecommerce.entity.RatingId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RatingRepositoryTest {
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void findAVGRatingOfProduct_WhenRequestValid_ExpectReturnFloat() {
        Rating rating1 = new Rating(new RatingId(1L, 1L), 5, "good");
        Rating rating2 = new Rating(new RatingId(2L, 1L), 4, "good");
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        float expected = 4.5F;
        float actual = ratingRepository.findAVGRatingOfProduct(1L).get();
        Assertions.assertEquals(expected, actual);
    }
}
