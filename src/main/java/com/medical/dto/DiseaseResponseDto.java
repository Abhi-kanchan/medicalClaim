package com.medical.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseResponseDto {
	
	private Integer diseaseId;
	private String diseaseName;
	private Double limitAmount;

}
