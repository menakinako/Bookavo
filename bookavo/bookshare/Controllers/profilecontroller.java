package com.bookavo.bookshare.Controllers;



import com.bookavo.bookshare.Models.CustomerUserDetails;

import com.bookavo.bookshare.Models.UserProfile;
import com.bookavo.bookshare.Services.UserProfileservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class profilecontroller {
    
    @Autowired
    private UserProfileservice proservice;
    @GetMapping("/setting.html")
    public String viewsettingpage(@AuthenticationPrincipal CustomerUserDetails user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("userProfile", new UserProfile());
        return"setting";
    }

    @PostMapping("/setting.html")
    public String processprofile( UserProfile userProfile){
        proservice.profile(userProfile);
      
    return"profile";
    }
}
