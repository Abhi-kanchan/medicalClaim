package com.medical.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.dto.LoginDTO;
import com.medical.dto.LoginResponseDTO;
import com.medical.exception.MedicalClaimException;
import com.medical.service.LoginService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mahesh
 *
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "*", "*/" }, allowedHeaders = { "*", "*/" })
@Slf4j
public class LoginController {


	@Autowired
	LoginService loginService;

	/**
	 * This method is used for Admin login to approve claim
	 * 
	 * @param LoginDTO
	 * @return LoginResponseDTO
	 * @throws MedicalClaimException 
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> getAdmin(@Valid @NotNull @RequestBody LoginDTO loginDTO) throws MedicalClaimException {
		log.debug("LoginController.class");
		return new ResponseEntity<>(loginService.getAdminDetails(loginDTO), HttpStatus.OK);

	}

}
