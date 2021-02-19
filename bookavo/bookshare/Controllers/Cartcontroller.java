package com.bookavo.bookshare.Controllers;

import com.bookavo.bookshare.Models.CartItem;
import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Services.Cartservice;
import com.bookavo.bookshare.Services.Userservice;

import org.springframework.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;

@Controller
public class Cartcontroller {
    @Autowired
    private Cartservice cartservice;

    @Autowired
    private Userservice service;

    @GetMapping("/mybooks.html")
    public String showmyBooks(Model model, @AuthenticationPrincipal Authentication authentication){
        User user = service.getCurrentlyLoggedInCustomer(authentication);
        List<CartItem> cartItems = cartservice.listCartitems(user);
     
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "My Books");
       

        return "mybooks";
    }
    
}
