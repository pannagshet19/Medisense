package com.healthpassport.config;

import com.healthpassport.model.*;
import com.healthpassport.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            DoctorRepository doctorRepo,
            PatientRepository patientRepo,
            AppointmentRepository appointmentRepo
    ){
        return args -> {

            /* ---------------- DOCTORS ---------------- */

            if(doctorRepo.count()==0){

                Doctor d1 = new Doctor();
                d1.setName("Dr Meghana Shet");
                d1.setSpecialization("Cardiology");
                d1.setLocation("New York");
                d1.setRating("4.9");

                Doctor d2 = new Doctor();
                d2.setName("Dr Satish Shet");
                d2.setSpecialization("Neurology");
                d2.setLocation("Boston");
                d2.setRating("4.8");

                doctorRepo.saveAll(List.of(d1,d2));

            }

            /* ---------------- PATIENT ---------------- */

            if(patientRepo.count()==0){

                Patient p = new Patient();
                p.setName("Sample Patient");
                p.setEmail("patient@test.com");

                patientRepo.save(p);

            }

            /* ---------------- APPOINTMENT ---------------- */

            if(appointmentRepo.count()==0){

                Appointment a = new Appointment();
                a.setDoctorId(1L);
                a.setPatientId(1L);
                a.setAppointmentDate(LocalDate.now());
                a.setTimeSlot("10:00 AM");

                appointmentRepo.save(a);

            }

        };
    }
}