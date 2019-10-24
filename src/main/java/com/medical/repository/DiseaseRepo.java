package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Disease;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Integer>{

}
