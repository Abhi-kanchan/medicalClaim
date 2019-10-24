package com.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medical.entity.Role;

@Repository
public interface LoginRepository extends JpaRepository<Role, Integer>{

	public Optional<Role> findByAdminNameAndAdminPassword(String userName, String password);

}
