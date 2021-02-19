package com.bookavo.bookshare.Services;

import javax.transaction.Transactional;

import com.bookavo.bookshare.Models.UserProfile;
import com.bookavo.bookshare.Repositories.UserProfilerepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class UserProfileservice {
    @Autowired
    UserProfilerepository prorepo;
    public UserProfile profile(UserProfile userProfile){

        return prorepo.save(userProfile);

    }
}
