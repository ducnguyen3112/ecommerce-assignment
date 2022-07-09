package com.nashtech.ecommerce.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

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
