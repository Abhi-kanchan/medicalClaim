
package com.medical.controller;

import javax.validation.Valid;

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

import com.medical.dto.ClaimDTO;
import com.medical.dto.ResponseDto;
import com.medical.exception.MedicalClaimException;
import com.medical.service.AddClaimService;

/**
 * This class adds the  medical claims
 * @author shiva
 * @version 1.0 
 * 
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
public class ClaimRequestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimRequestController.class);

	@Autowired
	AddClaimService addClaimService;

	/**
	 * This method validates the claim details if they are valid then the claim will
	 * be successfully added to DB.
	 * 
	 * @param ClaimDTO
	 * @return ResponseDto
	 * @exception MedicalClaimException
	 */
	@PostMapping("/claims")

	public ResponseEntity<ResponseDto> addClaim(@Valid @RequestBody ClaimDTO claimDTO) throws MedicalClaimException {

		LOGGER.info("ClaimRequestController");

		return new ResponseEntity<>(addClaimService.addClaim(claimDTO), HttpStatus.CREATED);

	}

}
