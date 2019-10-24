/**
 * 
 */
package com.medical.service;

import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.exception.MedicalClaimException;

/**
 * @author User1
 *
 */
public interface PolicyService {

	/**
	 * @param policyRequestDto
	 * @return PolicyRespnseDto
	 * @throws MedicalClaimException 
	 */
	PolicyResponseDto claimService(PolicyRequestDto policyRequestDto) throws MedicalClaimException;

}
