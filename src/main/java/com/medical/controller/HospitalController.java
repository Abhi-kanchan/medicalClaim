package com.medical.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medical.dto.HospitalResponseDto;
import com.medical.service.HospitalService;

/**
 *
 * @apiNote controller used to fetch list of Hospitals
 * @author Abhishek C
 * @version 1.0
 * @since 2019-10-21
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class HospitalController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	HospitalService hospitalService;

	/**
	 * @apiNote getHospitals method is used to fetch list of hospitals
	 * @param no parameters
	 * @return list of hospitals
	 * 
	 */
	@GetMapping("/hospitals")
	public ResponseEntity<List<HospitalResponseDto>> getHospitals() {
		LOGGER.debug("HospitalController class getHospitals method");
		List<HospitalResponseDto> list = hospitalService.getHospitalList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
