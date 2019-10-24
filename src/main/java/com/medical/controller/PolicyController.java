/**
 * 
 */
package com.medical.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.exception.MedicalClaimException;
import com.medical.service.PolicyService;

/**
 * @author akash
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class PolicyController {

	public static final Logger lOGGER = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	private PolicyService policyService;

	/**
	 * This method is used for given policy number is available or not and after
	 * this identify given policy number is valid or not based on aadhar number and
	 * date of birth
	 * 
	 * @param PolicyRequestDto
	 * @return PolicyResponseDto which returns userId,message, statusCode
	 * @throws MedicalClaimException
	 */
	@PostMapping("/policies")
	public ResponseEntity<PolicyResponseDto> policy(@NotNull @RequestBody PolicyRequestDto policyRequestDto)
			throws MedicalClaimException {
		lOGGER.info("inside policy controller");
		return new ResponseEntity<>(policyService.claimService(policyRequestDto), HttpStatus.ACCEPTED);

	}

}
