/**
 * 
 */
package com.medical.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.exception.MedicalClaimException;
import com.medical.service.LoginServiceImpl;
import com.medical.util.MedicalClaimConstants;

/**
 * @author User1
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	@Mock
	LoginServiceImpl loginServiceImpl;

	@InjectMocks
	LoginController loginController;

	@Test
	public void testGetAdmin() throws MedicalClaimException {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminName("Mahesh");
		loginDTO.setAdminPassword("mahesh");

		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setRoleId(1);
		loginResponseDTO.setAdminName("Mahesh");
		loginResponseDTO.setMessage(MedicalClaimConstants.LOGIN_SUCCESS);
		loginResponseDTO.setStatusCode(MedicalClaimConstants.LOGIN_SUCCESS_CODE);

		Mockito.when(loginServiceImpl.getAdminDetails(loginDTO)).thenReturn(loginResponseDTO);
		ResponseEntity<LoginResponseDTO> actualValue = loginController.getAdmin(loginDTO);
		assertEquals(loginResponseDTO.getStatusCode(), actualValue.getBody().getStatusCode());
	}

}
