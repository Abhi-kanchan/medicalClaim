package com.medical.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.exception.MedicalClaimException;
import com.medical.service.ApproveService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author mahesh
 *
 */

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
@Slf4j
public class ApproveController {

	@Autowired
	ApproveService approveService;

	/**
	 * this method is used to get claim details based on approved id
	 * 
	 * @param approverId
	 * @return ClaimResDto (claim details)
	 * @throws MedicalClaimException 
	 */

	@GetMapping("/approvers/{approverId}/claims")
	public ResponseEntity<List<ClaimResDto>> claimList(@Valid @NotNull @PathVariable Integer approverId) throws MedicalClaimException {
		log.info("claimList method in ApproveController");
		return new ResponseEntity<>(approveService.claimList(approverId), HttpStatus.OK);
	}

	/**
	 * this method is used to approve the claim details
	 * 
	 * @param ApproveReqDto
	 * @return ApproveResDto
	 * 
	 * @throws MedicalClaimException
	 */

	@PostMapping("/approvers")
	public ResponseEntity<ApproveResDto> approveClaim(@Valid @NotNull @RequestBody ApproveReqDto approveReqDto)
			throws MedicalClaimException {

		log.debug("approveClaim method in ApproveController");
		return new ResponseEntity<>(approveService.approveClaim(approveReqDto), HttpStatus.OK);
	}

}
