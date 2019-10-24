package com.medical.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Claim;
/**
 * 
 * @author mahesh
 *
 */
@Repository
public interface ClaimRepo extends JpaRepository<Claim, Integer>{

	Optional<List<Claim>> findByApproverId(Integer approverId);

	Optional<List<Claim>> findAllByApprStatus(String status);

	Optional<Claim> findByClaimId(Integer claimId);
	
	List<Claim> findAll();

	Optional<List<Claim>> findAllByOrderByPatientName();

	
}
