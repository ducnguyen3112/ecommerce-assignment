package com.nashtech.ecommerce.dto.response;

import com.nashtech.ecommerce.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseRoleDto {
	private RoleName name;
}
