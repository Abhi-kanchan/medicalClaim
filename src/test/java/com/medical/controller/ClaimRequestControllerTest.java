/**
 * 
 */
package com.medical.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.medical.dto.ClaimDTO;
import com.medical.dto.ResponseDto;
import com.medical.exception.MedicalClaimException;
import com.medical.service.AddClaimServiceImpl;
import com.medical.util.MedicalClaimConstants;

/**
 * @author User1
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ClaimRequestControllerTest {

	@Mock
	private AddClaimServiceImpl addClaimServiceImpl;

	@InjectMocks
	private ClaimRequestController claimRequestController;

	@Test
	public void testAddClaim() throws MedicalClaimException {

		ClaimDTO claimDTO = new ClaimDTO();

		claimDTO.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claimDTO.setDischargedDate(LocalDate.of(2019, 10, 13));
		claimDTO.setClaimAmount(2000D);
		claimDTO.setClaimId(1);
		claimDTO.setClaimNo(1234);
		claimDTO.setDiseaseId(1);
		claimDTO.setUserId(1);

		ResponseDto responseDTO = new ResponseDto();
		responseDTO.setMessage(MedicalClaimConstants.CLAIM_REQUEST_PASS);
		responseDTO.setStatusCode(MedicalClaimConstants.CLAIM_REQUEST_SUCCESS_CODE);

		Mockito.when(addClaimServiceImpl.addClaim(claimDTO)).thenReturn(responseDTO);

		ResponseEntity<ResponseDto> actualValue = claimRequestController.addClaim(claimDTO);
		assertEquals(responseDTO.getStatusCode(), actualValue.getBody().getStatusCode());

	}

}
