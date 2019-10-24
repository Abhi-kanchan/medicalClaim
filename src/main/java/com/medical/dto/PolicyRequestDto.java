/**
 * 
 */
package com.medical.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Akash
 * 
 *
 */
@Setter
@Getter
public class PolicyRequestDto {
	
	private Integer policyNo;
	private long aadharNo;
	private LocalDate dob;

}
