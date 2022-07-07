package com.nashtech.ecommerce.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nashtech.ecommerce.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseProductDto {

	private Long id;

	private String productName;

	private String description;

	private int inventory;

	private Long price;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifiedAt;

	private ProductStatus status;

	private String image;
	
	private float avgScores;
}
