package com.healthpassport.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private Map<String,String> otpStore = new HashMap<>();

    /* SEND OTP */

    @PostMapping("/send-otp")
    public Map<String,String> sendOtp(@RequestBody Map<String,String> body){

        String phone = body.get("phone");

        String otp = String.valueOf(new Random().nextInt(900000)+100000);

        otpStore.put(phone,otp);

        System.out.println("OTP for "+phone+" : "+otp);

        return Map.of("message","OTP Sent");
    }

    /* VERIFY OTP */

    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestBody Map<String,String> body){

        String phone = body.get("phone");
        String otp = body.get("otp");

        if(otp.equals(otpStore.get(phone))){

            return Map.of("message","OTP Verified");

        }

        return Map.of("message","Invalid OTP");

    }

    /* LOGIN */

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> body){

        String role = body.get("role");

        return Map.of(
                "message","Login success",
                "role",role
        );

    }

    /* REGISTER */

    @PostMapping("/register")
    public Map<String,String> register(@RequestBody Map<String,String> body){

        return Map.of("message","User registered");

    }

}