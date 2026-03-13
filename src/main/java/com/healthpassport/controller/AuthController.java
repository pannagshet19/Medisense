package com.healthpassport.controller;

import com.healthpassport.model.Patient;
import com.healthpassport.repository.PatientRepository;
import com.healthpassport.service.OtpService;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final OtpService otpService;
    private final PatientRepository patientRepo;

    public AuthController(OtpService otpService, PatientRepository patientRepo){
        this.otpService = otpService;
        this.patientRepo = patientRepo;
    }

    /* SEND OTP */

    @PostMapping("/send-otp")
    public Map<String,String> sendOtp(@RequestBody Map<String,String> req){

        String email = req.get("email");

        otpService.sendOtp(email);

        return Map.of("status","OTP_SENT");
    }

    /* VERIFY OTP */

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestBody Map<String,String> req){

        String email = req.get("email");
        String otp = req.get("otp");

        boolean valid = otpService.verifyOtp(email,otp);

        if(valid){
            return Map.of("status","SUCCESS");
        }

        return Map.of("status","FAILED");
    }

    /* REGISTER */

    @PostMapping("/register")
    public Patient register(@RequestBody Patient patient){

        return patientRepo.save(patient);

    }

}