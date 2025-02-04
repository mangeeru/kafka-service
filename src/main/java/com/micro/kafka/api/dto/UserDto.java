package com.micro.kafka.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;

}
