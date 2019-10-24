package com.medical.service;

import org.springframework.stereotype.Service;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.exception.MedicalClaimException;

@Service
public interface LoginService {
	
	public LoginResponseDTO getAdminDetails(LoginDTO loginDTO) throws MedicalClaimException;

}
