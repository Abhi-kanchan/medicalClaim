package com.medical.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medical.dto.HospitalResponseDto;

@Service
public interface HospitalService {
	
	List<HospitalResponseDto> getHospitalList();
}
