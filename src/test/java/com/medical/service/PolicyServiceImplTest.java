/**
 * 
 */
package com.medical.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.medical.dto.PolicyRequestDto;
import com.medical.dto.PolicyResponseDto;
import com.medical.entity.User;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.UserRepository;
import com.medical.util.MedicalClaimConstants;

/**
 * @author akash
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private PolicyServiceImpl policyServiceImpl;

	User user = null;
	PolicyRequestDto policyRequestDto = null;
	PolicyResponseDto policyResponseDto = null;

	/**
	 *
	 * Initial set up
	 */
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		user = new User();
		user.setUserId(1);
		user.setAadharNo(102l);
		user.setDob(LocalDate.now());
		user.setPolicyNo(10);

		policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNo(101);
		policyRequestDto.setAadharNo(102l);
		policyRequestDto.setDob(LocalDate.now());

		policyResponseDto = new PolicyResponseDto();
		policyResponseDto.setUserId(1);
		policyResponseDto.setMessage(MedicalClaimConstants.POLICY_MESSAGE);
		policyResponseDto.setStatusCode(MedicalClaimConstants.POLICY_STATUS_CODE);

	}

	/**
	 * @apiNote test case for claimService() method
	 * @return PolicyResponseDto
	 * @throws MedicalClaimException
	 */

	@Test
	public void testClaimService() throws MedicalClaimException {
		Mockito.when(userRepository.findByPolicyNo(Mockito.anyInt())).thenReturn(Optional.of(user));
		PolicyResponseDto actualResponseDto = policyServiceImpl.claimService(policyRequestDto);

		assertEquals(policyResponseDto.getStatusCode(), actualResponseDto.getStatusCode());

	}

	/**
	 * @throws MedicalClaimException
	 * @apiNote negative test case for claimService() method
	 * @throws PolicyNotFound
	 */

	@Test(expected = MedicalClaimException.class)
	public void testClaimServiceException() throws MedicalClaimException {

		// Mockito.when(userRepository.findByPolicyNo(Mockito.anyInt())).thenThrow(MedicalClaimException.class);
		policyServiceImpl.claimService(policyRequestDto);

	}

	@Test(expected = MedicalClaimException.class)
	public void testClaimServiceExceptionAadhar() throws MedicalClaimException {

		User user = new User();
		user.setUserId(1);
		user.setAadharNo(102l);
		user.setDob(LocalDate.now());
		user.setPolicyNo(10);
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNo(10);
		policyRequestDto.setAadharNo(123);
		Mockito.when(userRepository.findByPolicyNo(Mockito.anyInt())).thenReturn(Optional.of(user));

		policyServiceImpl.claimService(policyRequestDto);

	}

	@Test(expected = MedicalClaimException.class)
	public void testClaimServiceExceptionDob() throws MedicalClaimException {

		User user = new User();
		user.setUserId(1);
		user.setAadharNo(102l);
		user.setDob(LocalDate.now());
		user.setPolicyNo(10);
		PolicyRequestDto policyRequestDto = new PolicyRequestDto();
		policyRequestDto.setPolicyNo(10);
		policyRequestDto.setAadharNo(102l);
		policyRequestDto.setDob(LocalDate.of(2001, 02, 01));
		Mockito.when(userRepository.findByPolicyNo(Mockito.anyInt())).thenReturn(Optional.of(user));

		policyServiceImpl.claimService(policyRequestDto);

	}

}
