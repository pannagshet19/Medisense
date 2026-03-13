package com.healthpassport.repository;

import com.healthpassport.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

}