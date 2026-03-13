package com.healthpassport.controller;

import com.healthpassport.model.Appointment;
import com.healthpassport.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {

    private final AppointmentRepository appointmentRepo;

    public AppointmentController(AppointmentRepository appointmentRepo){
        this.appointmentRepo = appointmentRepo;
    }

    @PostMapping
    public Appointment bookAppointment(@RequestBody Appointment appointment){
        return appointmentRepo.save(appointment);
    }

}