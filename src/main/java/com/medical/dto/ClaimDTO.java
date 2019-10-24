/**
 * 
 */
package com.medical.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shiva
 *
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer claimId;
	private String patientName;
	private Integer claimNo;
	private Integer diseaseId;
	@NotNull
	@NotEmpty
	private LocalDate admissionDate;
	@NotNull
	@NotEmpty
	private LocalDate dischargedDate;
	private Integer hospitalId;

	@NotEmpty
	private Double claimAmount;
	private Integer userId;
	private String comments;
	private String status;

}
