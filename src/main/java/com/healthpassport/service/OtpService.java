package com.healthpassport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String,String> otpStore = new HashMap<>();

    public void sendOtp(String email){

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStore.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Your AI Health Passport OTP");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

    }

    public boolean verifyOtp(String email,String otp){

        String stored = otpStore.get(email);

        return stored != null && stored.equals(otp);

    }

}