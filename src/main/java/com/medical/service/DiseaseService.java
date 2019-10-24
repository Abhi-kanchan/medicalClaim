package com.medical.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.medical.dto.DiseaseResponseDto;

@Service
public interface DiseaseService {
	 
	List<DiseaseResponseDto> getDiseaseList();
}
