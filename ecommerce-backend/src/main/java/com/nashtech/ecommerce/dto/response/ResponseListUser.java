package com.nashtech.ecommerce.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseListUser {
	@JsonProperty("total")
	private Long totalUser;
	@JsonProperty("per_page")
	private int perPage;
	@JsonProperty("current_page")
	private int currentPage;
	@JsonProperty("last_page")
	private int lastPage;
	@JsonProperty("data")
	private List<ResponseUserDto> responseUserDtos;
}
