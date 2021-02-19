package com.bookavo.bookshare.Controllers;




import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Services.UserNotFoundException;
import com.bookavo.bookshare.Services.Userservice;
import com.bookavo.bookshare.Site.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {

    @Autowired
    private Userservice service;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(Model model){
       model.addAttribute(("pageTitle"), "Forgot Password");
       
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request, Model model){
           String email = request.getParameter("email");
           String token = RandomString.make(45);
           System.out.println("Email" + email);
           System.out.println("Token" + token);

           try{
           service.updateResetPasswordToken(token, email);

           String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
           sendEmail(email, resetPasswordLink);
           model.addAttribute("message", "We have sent reset password link to your mail.Please check.");
           }catch(UserNotFoundException ex){
               model.addAttribute("error", ex.getMessage());
           }catch(UnsupportedEncodingException | MessagingException e){
            model.addAttribute("error", "error while sending email.");
           }
        return "forgot_password_form";
    }
    private void sendEmail(String email, String resetPasswordLink)throws UnsupportedEncodingException, MessagingException{
        MimeMessage message  = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("techpractice32@gmail.com", "Bookavo Team");
        helper.setTo(email);

        String subject ="Here's the link for ResetPassword";
        String content ="<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                +"<p>Click the link below to change your password"
                + "<p><b><a href=\"" + resetPasswordLink +"\">ResetPassword</a></b></p>"
                +"<p>Ignore this email if yoy do remember your password or you have not made the request.</p>";
                
                helper.setSubject(subject);
                helper.setText(content, true);

                mailSender.send(message);

    }
    @GetMapping("/reset_password")
    public String showresetPasswordForm(@Param(value ="token") String token, Model model){
       User user = service.get(token);
       if(user == null){
           model.addAttribute("title", "Reset Your Password");
           model.addAttribute("message", "Invalid token");

           return "message";
       }
       model.addAttribute("token", token);

        return "reset_password_form";
    }
    @PostMapping("/reset_password")
    public String processResetPasswors(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = service.get(token);
        if(user == null){
            model.addAttribute("title", "Reset Your Password");
            model.addAttribute("message", "Invalid token");
 
        }else{
            service.updatePassword(user, password);
            model.addAttribute("message", "You have sucessfully changed your password.");
        }
        return "message";

    }
    
}
