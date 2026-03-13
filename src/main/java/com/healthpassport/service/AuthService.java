package com.healthpassport.service;

import com.healthpassport.model.Patient;
import com.healthpassport.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final PatientRepository patientRepository;

    public AuthService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public Patient register(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient login(String email, String password){

        Optional<Patient> patient = patientRepository.findByEmail(email);

        if(patient.isPresent() && patient.get().getPassword().equals(password)){
            return patient.get();
        }

        throw new RuntimeException("Invalid email or password");
    }

}