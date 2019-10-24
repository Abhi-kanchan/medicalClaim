package com.medical.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.exception.MedicalClaimException;

/**
 * 
 * @author mahesh
 *
 */
@Service
public interface ApproveService {

	List<ClaimResDto> claimList(@NotNull Integer approverId) throws MedicalClaimException;

	ApproveResDto approveClaim(ApproveReqDto approveReqDto) throws MedicalClaimException;

}
