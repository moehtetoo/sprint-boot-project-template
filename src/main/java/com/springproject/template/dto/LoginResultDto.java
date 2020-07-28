package com.springproject.template.dto;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.Setter;

@Getter
@Setter
@Accessors(chain = true)
public class LoginResultDto {
	
	private String name;
	private String email;
	private boolean success;
	private String message;
}
