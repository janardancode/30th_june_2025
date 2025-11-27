package com.kodewala.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kodewala.model.Reseller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResellerController {

    private static final List<Reseller> RESELLERS = new ArrayList<>();
    public static Reseller currentLoggedInReseller = null;

    private static int resellerSeq = 1;

    @GetMapping("/displayRegistrationForm")
    public String showRegistrationForm(Model model) {
        model.addAttribute("reseller", new Reseller());
        return "showRegisterForm";
    }

    @GetMapping("/register")
    public String handleGetRegister() {
        return "redirect:/displayRegistrationForm";
    }

    @PostMapping("/register")
    public String doRegistration(@ModelAttribute Reseller reseller, Model model) {

        reseller.setId(resellerSeq++);
        
        System.out.println("=== Reseller Registration Received ===");
        System.out.println(" Id        :: " + reseller.getId());
        System.out.println(" First Name :: " + reseller.getFirstName());
        System.out.println(" Last Name  :: " + reseller.getLastName());
        System.out.println(" Mobile     :: " + reseller.getMobile());
        System.out.println(" Email      :: " + reseller.getEmail());
        System.out.println(" Password   :: " + reseller.getPassword());

        RESELLERS.add(reseller);

        model.addAttribute("msg", "Registration successful! Please login.");
        return "reseller-login";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        return "reseller-login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute Reseller formData, Model model) {

        currentLoggedInReseller = null;

        for (Reseller r : RESELLERS) {
            if (r.getEmail().equals(formData.getEmail()) &&
                r.getPassword().equals(formData.getPassword())) {

                currentLoggedInReseller = r;

                System.out.println("=== Reseller Login Successful ===");
                System.out.println(" Id     :: " + r.getId());
                System.out.println(" Email  :: " + r.getEmail());
                System.out.println(" Mobile :: " + r.getMobile());
                System.out.println(" Name   :: " + r.getFirstName() + " " + r.getLastName());

                model.addAttribute("reseller", r);
                return "resellerHome";
            }
        }

        System.out.println("=== Login Failed for Email :: " + formData.getEmail() + " ===");

        model.addAttribute("errorMsg", "Invalid email or password");
        return "reseller-login";
    }

    @GetMapping("/home")
    public String showHome(Model model) {
        if (currentLoggedInReseller == null) {
            return "redirect:/login";
        }
        model.addAttribute("reseller", currentLoggedInReseller);
        return "resellerHome";
    }

    @GetMapping("/logout")
    public String doLogout() {
        if (currentLoggedInReseller != null) {
            System.out.println("=== Reseller Logout :: " + currentLoggedInReseller.getEmail() + " ===");
        }
        currentLoggedInReseller = null;
        return "redirect:/login";
    }
}
