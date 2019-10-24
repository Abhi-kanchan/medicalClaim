package com.medical.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.entity.Claim;
import com.medical.exception.MedicalClaimException;
import com.medical.service.ApproveService;
import com.medical.util.MedicalClaimConstants;

/**
 * author mahesh
 */
@RunWith(MockitoJUnitRunner.class)
public class ApproveControllerTest {

	@Mock
	ApproveService approveService;

	@InjectMocks
	ApproveController approveController;

	@Test
	public void testClaimList() throws MedicalClaimException {
		List<ClaimResDto> list = new ArrayList<>();

		Claim claim1 = new Claim();
		claim1.setPatientName("Raj");
		ClaimResDto claimResDto1 = new ClaimResDto();
		claimResDto1.setPatientName(claim1.getPatientName());

		Claim claim2 = new Claim();
		claim2.setPatientName("Ramana");
		ClaimResDto claimResDto2 = new ClaimResDto();
		claimResDto2.setPatientName(claim2.getPatientName());

		list.add(claimResDto1);
		list.add(claimResDto2);

		Mockito.when(approveService.claimList(2)).thenReturn(list);
		ResponseEntity<List<ClaimResDto>> obj = approveController.claimList(2);
		assertEquals(list.size(), obj.getBody().size());
	}

	@Test
	public void testApproveClaim() throws MedicalClaimException {
		ApproveReqDto approveReqDto = new ApproveReqDto();
		approveReqDto.setApproverId(1);
		approveReqDto.setClaimId(1);
		approveReqDto.setComment("NICE");
		approveReqDto.setStatus("APPROVED");

		ApproveResDto approveResDto = new ApproveResDto();
		approveResDto.setMessage(MedicalClaimConstants.UPDATED);
		approveResDto.setStatusCode(HttpStatus.OK.value());

		Mockito.when(approveService.approveClaim(approveReqDto)).thenReturn(approveResDto);
		ResponseEntity<ApproveResDto> actualValue = approveController.approveClaim(approveReqDto);
		assertEquals(approveResDto.getStatusCode(), actualValue.getBody().getStatusCode());
	}

}
