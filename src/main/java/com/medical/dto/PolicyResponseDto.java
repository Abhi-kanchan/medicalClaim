/**
 * 
 */
package com.medical.dto;


import lombok.Getter;
import lombok.Setter;

/**
 * @author akash
 *
 */

@Setter
@Getter
public class PolicyResponseDto {
	
	private Integer userId;
	private String message;
	private Integer statusCode;

}
