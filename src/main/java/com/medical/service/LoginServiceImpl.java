
package com.medical.service;

import static com.medical.util.MedicalClaimConstants.CREDENTIALS_EMPTY;
import static com.medical.util.MedicalClaimConstants.LOGIN_FAILURE;
import static com.medical.util.MedicalClaimConstants.LOGIN_SUCCESS;
import static com.medical.util.MedicalClaimConstants.LOGIN_SUCCESS_CODE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.entity.Role;
import com.medical.exception.MedicalClaimException;
import com.medical.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahesh
 * @since 1.0
 *
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginRepository loingRepository;

	/**
	 * method is used to check the login details and also check whether the
	 * credentials are correct or in correct
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 * @throws MedicalClaimException
	 * 
	 */
	@Override
	public LoginResponseDTO getAdminDetails(LoginDTO loginDTO) throws MedicalClaimException {

		log.info("Inside LoginServiceImpl");

		String userName = loginDTO.getAdminName();
		String password = loginDTO.getAdminPassword();

		log.info("userName:{} Password:{}", userName, password);

		LoginResponseDTO loginResponseDTO = null;

		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			throw new MedicalClaimException(CREDENTIALS_EMPTY);
		} else {
			Optional<Role> role = loingRepository.findByAdminNameAndAdminPassword(userName, password);
			if (role.isPresent()) {
				loginResponseDTO = new LoginResponseDTO();
				loginResponseDTO.setAdminName(role.get().getAdminName());
				loginResponseDTO.setRoleId(role.get().getRoleId());
				loginResponseDTO.setMessage(LOGIN_SUCCESS);
				loginResponseDTO.setStatusCode(LOGIN_SUCCESS_CODE);

			} else {
				throw new MedicalClaimException(LOGIN_FAILURE);
			}
		}
		return loginResponseDTO;
	}

}
