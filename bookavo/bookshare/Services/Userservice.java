package com.bookavo.bookshare.Services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import com.bookavo.bookshare.Models.CustomerUserDetails;
import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Repositories.Userrepository;

import org.springframework.security.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Transactional
public class Userservice {
    @Autowired
    private Userrepository repo;
    @Autowired
    private JavaMailSender mailSender;


    public User registeruser(User user)
    {
        BCryptPasswordEncoder  encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setEnabled(false);
         
        String randomcode = RandomString.make(64);
        user.setVerificationCode(randomcode);

        return repo.save(user);
    }

    public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException{
      String subject ="Please verify your Registration";
      String sendername ="Bookavo Team";
      String mailContent ="<p>Hi" + user.getFirstName() + ",</p>";
      mailContent += "<p>We re excited to have you get started.<br>Please click the link below to verify your registration:</p>";
      String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
      mailContent += "<h3><a href=\"" + verifyURL +"\">VERIFY</a></h3>";     
      mailContent += "<p>Thank you<br>The bookavo Team</p>";

      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);

      helper.setFrom("techpractice32@gmail.com", sendername);
      helper.setTo(user.getEmail());
      helper.setSubject(subject);

      helper.setText(mailContent, true);
      mailSender.send(message);
    }

    public boolean verify(String verificationCode){
      User user = repo.findByVerificationCode(verificationCode);
      if(user == null || user.isEnabled()){
        return false;
      }
      else{
        repo.enable(user.getId());
        return true;
      }
    }
   
    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException{
      User user = repo.findByEmail(email);
      if(user != null){
        user.setResetPasswordToken(token);
        repo.save(user);
      }
      else{
        throw new UserNotFoundException("Could not find any user with email " + email);
      }
    }

    public User get(String resetPasswordToken){
      return repo.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword(User user, String newPassword){
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodePassword = passwordEncoder.encode(newPassword);

      user.setPassword(encodePassword);
      user.setResetPasswordToken(null);

      repo.save(user);
    }

    public User getCurrentlyLoggedInCustomer(Authentication authentication){

      if (authentication == null){ return null;}
      User user = null;
      Object principal = authentication.getPrincipal();
      
      if(principal instanceof CustomerUserDetails){
        user =((CustomerUserDetails)principal).getUser();
       
      }
      
      return user;
    }

    public User getUserByEmail(String email){
      return repo.findByEmail(email);
    }

}
