package com.healthpassport.controller;

import com.healthpassport.repository.*;
import com.healthpassport.model.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctor")
@CrossOrigin
public class DoctorDashboardController {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final ReportRepository reportRepo;

    public DoctorDashboardController(
            AppointmentRepository appointmentRepo,
            PatientRepository patientRepo,
            ReportRepository reportRepo
    ){
        this.appointmentRepo = appointmentRepo;
        this.patientRepo = patientRepo;
        this.reportRepo = reportRepo;
    }

    // =============================
    // DASHBOARD STATS
    // =============================

    @GetMapping("/stats")
    public Map<String,Long> getStats(){

        long todayAppointments =
                appointmentRepo.countByAppointmentDate(LocalDate.now());

        long totalPatients = patientRepo.count();

        long totalReports = reportRepo.count();

        return Map.of(
                "todayAppointments", todayAppointments,
                "pendingReports", totalReports,
                "totalPatients", totalPatients,
                "completedToday", todayAppointments
        );
    }

    // =============================
    // TODAY APPOINTMENTS
    // =============================

    @GetMapping("/appointments")
    public List<Appointment> getTodayAppointments(){

        return appointmentRepo.findByAppointmentDate(LocalDate.now());

    }

    // =============================
    // RECENT REPORTS
    // =============================

    @GetMapping("/reports")
    public List<Report> getRecentReports(){

        return reportRepo.findTop5ByOrderByIdDesc();

    }

}