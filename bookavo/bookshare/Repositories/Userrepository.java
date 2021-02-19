package com.bookavo.bookshare.Repositories;

import com.bookavo.bookshare.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends JpaRepository<User, Long> {
   
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("UPDATE User u SET u.enabled = true WHERE u.id = ?1")
    @Modifying
    public void enable(Long id);
      
    public User findByResetPasswordToken(String token);
    
    @Query("SELECT u FROM User u WHERE u.verificationcode = ?1")
    public User findByVerificationCode(String code);
   
}