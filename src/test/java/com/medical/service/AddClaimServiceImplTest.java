package com.medical.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.medical.dto.ClaimDTO;
import com.medical.dto.ResponseDto;
import com.medical.entity.Claim;
import com.medical.entity.Disease;
import com.medical.entity.User;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.ClaimRepository;
import com.medical.repository.DiseaseRepository;
import com.medical.repository.UserRepository;
import com.medical.util.MedicalClaimConstants;

@RunWith(MockitoJUnitRunner.class)
public class AddClaimServiceImplTest {

	@Mock
	ClaimRepository claimRepository;

	@Mock
	DiseaseRepository diseaseRepository;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	AddClaimServiceImpl addClaimServiceImpl;

	Claim claim = null;

	Disease disease = null;
	User user = null;
	ResponseDto responseDTO = null;
	ClaimDTO claimDTO = null;

	@Before
	public void setup() {
		claim = new Claim();
		claim.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claim.setDischargedDate(LocalDate.of(2019, 10, 13));
		claim.setClaimAmount(2000D);
		claim.setClaimId(1);
		claim.setClaimNo(1234);
		claim.setDiseaseId(1);
		claim.setUserId(1);
		disease = new Disease();
		disease.setDiseaseId(1);
		disease.setLimitAmount(10000D);

		user = new User();
		user.setUserId(1);
		responseDTO = new ResponseDto();
		responseDTO.setMessage(MedicalClaimConstants.CLAIM_REQUEST_PASS);
		responseDTO.setStatusCode(MedicalClaimConstants.CLAIM_REQUEST_SUCCESS_CODE);

		claimDTO = new ClaimDTO();
		claimDTO.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claimDTO.setDischargedDate(LocalDate.of(2019, 10, 13));
		claimDTO.setClaimAmount(2000D);
		claimDTO.setClaimId(1);
		claimDTO.setClaimNo(1234);
		claimDTO.setDiseaseId(1);
		claimDTO.setUserId(1);
	}

	@Test
	public void addClaimTest() throws MedicalClaimException {

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(diseaseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(disease));
		Mockito.when(claimRepository.save(Mockito.any())).thenReturn(claim);

		ResponseDto actualValue = addClaimServiceImpl.addClaim(claimDTO);

		assertEquals(responseDTO.getStatusCode(), actualValue.getStatusCode());

	}

	@Test(expected = MedicalClaimException.class)
	public void addClaimUserTest() throws MedicalClaimException {

		User user = new User();
		user.setUserId(1);

		ClaimDTO claimDTO = new ClaimDTO();

		claimDTO.setUserId(22);

		addClaimServiceImpl.addClaim(claimDTO);

	}

	@Test(expected = MedicalClaimException.class)
	public void addClaimDiseaseTest() throws MedicalClaimException {

		ClaimDTO claimDTO = new ClaimDTO();

		Disease disease = new Disease();
		disease.setDiseaseId(1);
		disease.setLimitAmount(1000D);

		claimDTO.setUserId(1);
		claimDTO.setDiseaseId(1);
		claimDTO.setClaimAmount(1000000D);

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		addClaimServiceImpl.addClaim(claimDTO);

	}

	@Test(expected = MedicalClaimException.class)
	public void addClaimDateTest() throws MedicalClaimException {

		ClaimDTO claimDTO = new ClaimDTO();

		Disease disease = new Disease();
		disease.setDiseaseId(1);
		disease.setLimitAmount(1000D);

		claimDTO.setUserId(1);
		claimDTO.setDiseaseId(1);
		claimDTO.setClaimAmount(1000000D);
		claimDTO.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claimDTO.setDischargedDate(LocalDate.of(2019, 10, 01));

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(diseaseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(disease));

		addClaimServiceImpl.addClaim(claimDTO);

	}

	@Test(expected = MedicalClaimException.class)
	public void addClaimAmountTest() throws MedicalClaimException {

		Disease disease = new Disease();
		disease.setDiseaseId(1);
		disease.setLimitAmount(1000D);
		ClaimDTO claimDTO = new ClaimDTO();
		claimDTO.setUserId(1);
		claimDTO.setDiseaseId(1);
		claimDTO.setClaimAmount(1000000D);
		claimDTO.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claimDTO.setDischargedDate(LocalDate.of(2019, 10, 13));

		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		Mockito.when(diseaseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(disease));

		addClaimServiceImpl.addClaim(claimDTO);

	}

}
