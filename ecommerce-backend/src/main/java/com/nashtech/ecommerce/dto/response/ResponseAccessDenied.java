package com.nashtech.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ResponseAccessDenied {
    private int status;
    private String message;
    private String error;
    private Date timestamp;

}
