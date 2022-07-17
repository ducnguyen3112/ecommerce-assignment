package com.nashtech.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseListRating {
    Long userId;
    String fullName;
    String avatar;
    String comment;
    float scores;

}
