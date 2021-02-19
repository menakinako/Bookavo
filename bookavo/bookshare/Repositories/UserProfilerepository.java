package com.bookavo.bookshare.Repositories;


import com.bookavo.bookshare.Models.UserProfile;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
@Repository
public interface UserProfilerepository extends JpaRepository<UserProfile , Integer> {
    
}
