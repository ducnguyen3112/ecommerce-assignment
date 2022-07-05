package com.nashtech.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseRatingDto {
	private Long userId;
	private Long productId;
	private int scores;
	private String comment;
}
