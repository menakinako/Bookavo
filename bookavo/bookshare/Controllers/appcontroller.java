package com.bookavo.bookshare.Controllers;



import com.bookavo.bookshare.Models.User;


import com.bookavo.bookshare.Services.Userservice;
import com.bookavo.bookshare.Site.Utility;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import com.bookavo.bookshare.Models.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;






@Controller

public class appcontroller {
    
  
    @Autowired
    private Userservice service;
    
    

    @GetMapping("/index.html")
    public String viewHomePage(@AuthenticationPrincipal CustomerUserDetails user, Model model){
        model.addAttribute("user", user);
        return"index";
    }
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
    @PostMapping("/logout")
    public String logout(){
        return "login";
    }

    @GetMapping("/register.html")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return"register";
    }

    @PostMapping("/register.html")
    public String processRegistration(User user, HttpServletRequest request)throws UnsupportedEncodingException, MessagingException{
        
       service.registeruser(user);
       String siteURL = Utility.getSiteURL(request);
       service.sendVerificationEmail(user, siteURL);
       return "check";
    }
    
    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model model){
        boolean verified = service.verify(code);

        String pageTitle = verified?"Verification Succeeded!": "Verification Failed";
        model.addAttribute("pageTitle", pageTitle);

        return (verified ?"login" : "verify_fail");

    }
    @GetMapping("/profile.html")
    public String viewprofilePage(@AuthenticationPrincipal CustomerUserDetails user, Model model ){
        model.addAttribute("user", user);
        
        return"profile";
    }
    
    @GetMapping("/fields.html")
    public String viewfieldsPage(){
        return "fields";
    }
    
    
    @GetMapping("/about.html")
    public String viewaboutPage(){
        return "about";
    }
}
