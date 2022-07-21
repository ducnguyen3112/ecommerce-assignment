package com.nashtech.ecommerce.service.impl;

import com.nashtech.ecommerce.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestEditUser {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "\\d*")
    private String phoneNumber;
    private Set<String> roles;
    private String address;
    private String avatar;
    private UserStatus status;
}
