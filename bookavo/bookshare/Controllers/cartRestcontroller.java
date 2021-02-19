package com.bookavo.bookshare.Controllers;

import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Services.Cartservice;
import com.bookavo.bookshare.Services.Userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.*;

@RestController
public class cartRestcontroller {

    @Autowired
    private Cartservice cartservice;

    @Autowired
    private Userservice service;

    @PostMapping("/cart/add/{eid}")
    public String addProductToCart(@PathVariable("eid") Integer enggId,
                       @AuthenticationPrincipal Authentication authentication){
                           if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
                               return "You must be logged in to add this book";
                           }
                            System.out.println("addto cart" + enggId);
                           User user = service.getCurrentlyLoggedInCustomer(authentication);

                           cartservice.addProduct1(enggId, user);

                           return "Book added to ur Mybooks";
                       }

                       @PostMapping("/cart/remove/{eid}")
                       public String removeProductFromCart(@PathVariable("eid") Integer enggId,
                                          @AuthenticationPrincipal Authentication authentication){
                                              if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
                                                  return "You must be logged in to remove books";
                                              }
                   
                                              User user = service.getCurrentlyLoggedInCustomer(authentication);

                                              if(user == null){
                                                  return "You must be logged in to remove books";
                                              }
                   
                                              cartservice.removeEngg(enggId, user);
                   
                                              return "The book has been remove from Mybooks";
                                          }                   
    
}
