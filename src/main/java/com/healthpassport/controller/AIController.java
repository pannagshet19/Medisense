package com.healthpassport.controller;

import com.healthpassport.service.AIService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService){
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public Map<String,String> analyzeReport(@RequestParam("file") MultipartFile file){

        try{

            PDDocument document = PDDocument.load(file.getInputStream());

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            String prompt =
                    "Analyze this medical report.\n\n"

                            + "Return in this format:\n\n"

                            + "HEALTH SUMMARY\n"
                            + "Explain patient's health.\n\n"

                            + "Possible Risks\n"
                            + "- risk 1\n"
                            + "- risk 2\n\n"

                            + "Suggestions\n"
                            + "- suggestion 1\n"
                            + "- suggestion 2\n\n"

                            + "Report:\n" + text;

            String result = aiService.analyzeHealth(prompt);

            return Map.of("summary", result);

        }
        catch(Exception e){

            e.printStackTrace();

            return Map.of("summary","AI analysis failed");

        }

    }

    @PostMapping("/xray")
    public Map<String,String> analyzeXray(@RequestParam("file") MultipartFile file){

        try{

            String prompt =
                    "You are a medical radiology AI.\n\n"

                            + "Assume you have analyzed a chest X-ray image uploaded by the patient.\n\n"

                            + "Return ONLY in this format:\n\n"

                            + "HEALTH SUMMARY\n"
                            + "Explain lung condition in simple terms.\n\n"

                            + "Possible Risks\n"
                            + "- risk 1\n"
                            + "- risk 2\n\n"

                            + "Suggestions\n"
                            + "- suggestion 1\n"
                            + "- suggestion 2";

            String result = aiService.analyzeHealth(prompt);

            return Map.of("summary", result);

        }
        catch(Exception e){

            e.printStackTrace();

            return Map.of("summary","X-ray AI analysis failed");

        }

    }
}