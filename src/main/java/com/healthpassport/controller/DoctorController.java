package com.healthpassport.controller;

import com.healthpassport.model.Doctor;
import com.healthpassport.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin
public class DoctorController {

    private final DoctorRepository doctorRepo;

    public DoctorController(DoctorRepository doctorRepo){
        this.doctorRepo = doctorRepo;
    }

    @GetMapping
    public List<Doctor> getDoctors(){
        return doctorRepo.findAll();
    }

}