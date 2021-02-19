package com.bookavo.bookshare;

import com.bookavo.bookshare.Models.CustomerUserDetails;
import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Repositories.Userrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsService implements UserDetailsService {
    
    @Autowired
    private Userrepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = repo.findByEmail( email);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerUserDetails(user);
    }
    
}
