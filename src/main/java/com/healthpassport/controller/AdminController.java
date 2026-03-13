package com.healthpassport.controller;

import com.healthpassport.repository.*;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final AppointmentRepository appointmentRepo;
    private final ReportRepository reportRepo;

    public AdminController(
            PatientRepository patientRepo,
            DoctorRepository doctorRepo,
            AppointmentRepository appointmentRepo,
            ReportRepository reportRepo
    ){
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.appointmentRepo = appointmentRepo;
        this.reportRepo = reportRepo;
    }

    @GetMapping("/stats")
    public Map<String,Long> getStats(){

        return Map.of(
                "patients", patientRepo.count(),
                "doctors", doctorRepo.count(),
                "appointments", appointmentRepo.count(),
                "reports", reportRepo.count()
        );

    }

}