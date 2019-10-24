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

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimApproval {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Integer claimApprovalId;
		private Integer calimId;
		private Integer approverId;
		private LocalDate approvedDate;
		private String status;
		private String comments;
		
}
