package com.medical.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimResDto {
	
	private String patientName;	
	private String diseaseName;
	private LocalDate admissionDate;
	private LocalDate dischargedDate;
	private String hospitalName;
	private Double claimAmount;
	private Double limitAmount;
    private String apprStatus;
    private String comments;
    private Integer claimId;
	
}
