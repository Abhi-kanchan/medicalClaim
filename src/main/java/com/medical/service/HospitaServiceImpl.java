package com.medical.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.dto.HospitalResponseDto;
import com.medical.entity.Hospital;
import com.medical.exception.HospitalNotFoundException;
import com.medical.repository.HospitalRepository;
import com.medical.util.MedicalClaimConstants;

/**
 * HospitaServiceImpl is used to fetch list of Hospitals
 * @author Abhishek C
 * @version 1.0
 * @since 2019-10-21
 * 
 */
@Service
public class HospitaServiceImpl implements HospitalService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HospitaServiceImpl.class);

	@Autowired
	HospitalRepository hospitalRepository;

	/**
	 * 
	 * getHospitalList method is used to fetch list of hospitals
	 * @return list of hospitals
	 * @throws HospitalNotFoundException
	 */
	@Override
	public List<HospitalResponseDto> getHospitalList() {
		LOGGER.info("inside hospital list");
		List<Hospital> list = hospitalRepository.findAll();
		if (list.isEmpty()) {
			throw new HospitalNotFoundException(MedicalClaimConstants.HOSPITAL_NOT_FOUND);
		}
		List<HospitalResponseDto> listhospital = new ArrayList<>();
		list.stream().forEach(s -> {
			HospitalResponseDto response = new HospitalResponseDto();
			BeanUtils.copyProperties(s, response);
			listhospital.add(response);
		});
		return listhospital;
	}

}
