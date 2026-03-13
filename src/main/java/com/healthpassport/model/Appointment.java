package com.healthpassport.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private String timeSlot;

    public Appointment(){}

    public Long getId(){
        return id;
    }

    public Long getPatientId(){
        return patientId;
    }

    public void setPatientId(Long patientId){
        this.patientId = patientId;
    }

    public Long getDoctorId(){
        return doctorId;
    }

    public void setDoctorId(Long doctorId){
        this.doctorId = doctorId;
    }

    public LocalDate getAppointmentDate(){
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate){
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot(){
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot){
        this.timeSlot = timeSlot;
    }
}