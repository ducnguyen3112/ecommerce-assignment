package com.nashtech.ecommerce.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class RequestSignInDto {
    @Email
    private String email;
    @NotEmpty
    @Min(value = 8)
    private String password;
}
