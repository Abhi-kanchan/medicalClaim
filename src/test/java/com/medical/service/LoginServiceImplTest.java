/**
 * 
 */
package com.medical.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.entity.Role;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.LoginRepository;

/**
 * @author User1
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {

	@Mock
	LoginRepository loginRepository;
	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	Role roll = null;

	@Before
	public void setup() {

		roll = new Role();
		roll.setRoleId(1);
		roll.setAdminName("Mahesh");
		roll.setAdminPassword("mahesh");

	}

	@Test
	public void testGetAdminDetails() throws MedicalClaimException {

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAdminName("Mahesh");
		;
		loginDTO.setAdminPassword("mahesh");
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
		loginResponseDTO.setStatusCode(201);
		Mockito.when(loginRepository.findByAdminNameAndAdminPassword(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.of(roll));
		LoginResponseDTO actualValue = loginServiceImpl.getAdminDetails(loginDTO);
		assertEquals(loginResponseDTO.getStatusCode(), actualValue.getStatusCode());

	}

}
