package com.nashtech.ecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@Getter
@Setter
public class ResponseSignIn {
    private String name;
    private String email;
    private Collection<? extends GrantedAuthority> roles;
    private String token;
}
