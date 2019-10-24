/**
 * 
 */
package com.medical.entity;

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
public class Disease {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer diseaseId;
	private String diseaseName;
	private Double limitAmount;
}
