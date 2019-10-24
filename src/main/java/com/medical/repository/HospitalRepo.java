package com.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Integer>{

}
