package com.medical.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.medical.dto.ApproveReqDto;
import com.medical.dto.ApproveResDto;
import com.medical.dto.ClaimResDto;
import com.medical.entity.Claim;
import com.medical.entity.ClaimApproval;
import com.medical.entity.Disease;
import com.medical.entity.Hospital;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.ClaimApprovalRepo;
import com.medical.repository.ClaimRepo;
import com.medical.repository.DiseaseRepo;
import com.medical.repository.HospitalRepo;
import com.medical.util.MedicalClaimConstants;

@RunWith(MockitoJUnitRunner.class)
public class ApproveServiceImplTest {

	@Mock
	ClaimRepo claimRepo;

	@Mock
	DiseaseRepo diseaseRepo;

	@Mock
	HospitalRepo hospitalRepo;

	@Mock
	ClaimApprovalRepo claimApprovalRepo;

	@InjectMocks
	ApproveServiceImpl approveServiceImpl;

	Claim claim = null;
	List<Claim> claimList = null;
	ClaimResDto claimResDto = null;
	List<ClaimResDto> claimResDtoList = null;

	Hospital hospital = null;
	Disease disease = null;

	@Before
	public void setup() {
		claim = new Claim();
		claim.setClaimId(1);
		claim.setClaimNo(1234);
		claim.setHospitalId(1);
		claim.setDiseaseId(1);
		claim.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claim.setDischargedDate(LocalDate.of(2019, 10, 13));
		claim.setApprStatus(MedicalClaimConstants.PENDING);
		claimList = new ArrayList<>();
		claimList.add(claim);

		claimResDto = new ClaimResDto();
		claimResDto.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claimResDto.setDischargedDate(LocalDate.of(2019, 10, 13));

		claimResDtoList = new ArrayList<>();
		claimResDtoList.add(claimResDto);
		hospital = new Hospital();
		hospital.setHospitalName("RAJA");
		disease = new Disease();
		disease.setDiseaseName("FEVER");

	}

	@Test
	public void testClaimList() throws MedicalClaimException {

		Mockito.when(claimRepo.findAllByOrderByPatientName()).thenReturn(Optional.of(claimList));
		Mockito.when(diseaseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(disease));
		Mockito.when(hospitalRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(hospital));

		List<ClaimResDto> actualValue = approveServiceImpl.claimList(1);
		assertEquals(claimResDtoList.size(), actualValue.size());

	}

	@Test
	public void testClaimListElse() throws MedicalClaimException {
		List<Claim> claimAppr=new ArrayList<>();
		Claim claim = new Claim();
		claim.setClaimId(1);
		claim.setClaimNo(1234);
		claim.setHospitalId(1);
		claim.setDiseaseId(1);
		claim.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claim.setDischargedDate(LocalDate.of(2019, 10, 13));
		claim.setApprStatus(MedicalClaimConstants.APPROVED);
		claimAppr.add(claim);


		Mockito.when(claimRepo.findAllByOrderByPatientName()).thenReturn(Optional.of(claimAppr));

		Mockito.when(diseaseRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(disease));
		Mockito.when(hospitalRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(hospital));

		List<ClaimResDto> actualValue = approveServiceImpl.claimList(2);

		assertEquals(claimResDtoList.size(), actualValue.size());

	}

	@Test(expected = MedicalClaimException.class)
	public void testClaimListEmpty() throws MedicalClaimException {

		// Mockito.when(claimRepo.findAllByOrderByPatientName()).thenReturn(Optional.of(claimList));

		approveServiceImpl.claimList(222);

	}
	
	@Test(expected = MedicalClaimException.class)
	public void testClaimListPRAEmpty() throws MedicalClaimException {
		
		List<Claim> claimAppr=new ArrayList<>();
		Claim claim = new Claim();
		claim.setClaimId(1);
		claim.setClaimNo(1234);
		claim.setHospitalId(1);
		claim.setDiseaseId(1);
		claim.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claim.setDischargedDate(LocalDate.of(2019, 10, 13));
		claim.setApprStatus("AAAA");
		claimAppr.add(claim);

		 Mockito.when(claimRepo.findAllByOrderByPatientName()).thenReturn(Optional.of(claimAppr));

		approveServiceImpl.claimList(1);

	}


	@Test(expected = MedicalClaimException.class)
	public void testClaimListPendingEmpty() throws MedicalClaimException {
		
		List<Claim> claimAppr=new ArrayList<>();
		Claim claim = new Claim();
		claim.setClaimId(1);
		claim.setClaimNo(1234);
		claim.setHospitalId(1);
		claim.setDiseaseId(1);
		claim.setAdmissionDate(LocalDate.of(2019, 10, 10));
		claim.setDischargedDate(LocalDate.of(2019, 10, 13));
		claim.setApprStatus(MedicalClaimConstants.APPROVED);
		claimAppr.add(claim);

		 Mockito.when(claimRepo.findAllByOrderByPatientName()).thenReturn(Optional.of(claimAppr));

		approveServiceImpl.claimList(1);

	}

	@Test(expected = MedicalClaimException.class)
	public void testApproveClaimElse() throws MedicalClaimException {
		ApproveReqDto approveReqDto = new ApproveReqDto();
		approveReqDto.setApproverId(1);
		approveReqDto.setClaimId(99);
		approveReqDto.setComment("NICE");
		approveReqDto.setStatus("APPROVED");

		ClaimApproval claimApproval = new ClaimApproval();
		claimApproval.setApprovedDate(LocalDate.now());
		claimApproval.setApproverId(1);
		claimApproval.setCalimId(1);
		claimApproval.setStatus("APPROVED");
		claimApproval.setComments("NICE");
		claimApproval.setClaimApprovalId(1);

		ApproveResDto approveResDto = new ApproveResDto();
		approveResDto.setMessage(MedicalClaimConstants.UPDATED);
		approveResDto.setStatusCode(HttpStatus.OK.value());

		approveServiceImpl.approveClaim(approveReqDto);
	}

	@Test
	public void testApproveClaim() throws MedicalClaimException {
		ApproveReqDto approveReqDto = new ApproveReqDto();
		approveReqDto.setApproverId(1);
		approveReqDto.setClaimId(1);
		approveReqDto.setComment("NICE");
		approveReqDto.setStatus("APPROVED");

		ClaimApproval claimApproval = new ClaimApproval();
		claimApproval.setApprovedDate(LocalDate.now());
		claimApproval.setApproverId(1);
		claimApproval.setCalimId(1);
		claimApproval.setStatus("APPROVED");
		claimApproval.setComments("NICE");
		claimApproval.setClaimApprovalId(1);

		ApproveResDto approveResDto = new ApproveResDto();
		approveResDto.setMessage(MedicalClaimConstants.UPDATED);
		approveResDto.setStatusCode(HttpStatus.OK.value());

		Mockito.when(claimRepo.findByClaimId(Mockito.anyInt())).thenReturn(Optional.of(claim));
		Mockito.when(claimRepo.save(Mockito.any())).thenReturn(claim);
		Mockito.when(claimApprovalRepo.save(Mockito.any())).thenReturn(claimApproval);

		ApproveResDto actualValue = approveServiceImpl.approveClaim(approveReqDto);

		assertEquals(approveResDto.getStatusCode(), actualValue.getStatusCode());

	}

}
