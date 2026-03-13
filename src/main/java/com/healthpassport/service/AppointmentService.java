package com.healthpassport.service;

import com.healthpassport.model.Appointment;
import com.healthpassport.repository.AppointmentRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    public Appointment bookAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getPatientAppointments(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<Appointment> getDoctorAppointments(Long doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }
}