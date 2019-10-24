
/**
 * 
 */
package com.medical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Integer> {
	
	List<Disease> findAll();
}

