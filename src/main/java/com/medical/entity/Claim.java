/**
 * 
 */
package com.medical.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author user1
 *
 */

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer claimId;
	private String patientName;
	private Integer claimNo;
	private Integer diseaseId;
	private LocalDate admissionDate;
	private LocalDate dischargedDate;
	private Integer hospitalId;
	private Double claimAmount;
	private Integer userId;
	private Integer approverId;
	private String apprStatus;
	private String comments;
	private LocalDate claimReqDate;

}
