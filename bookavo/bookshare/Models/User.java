package com.bookavo.bookshare.Models;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.Lob;

import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length= 20)
    private String firstname;
    @Column(nullable = false, length= 20)
    private String lastname;
    @Column(nullable = false, unique = true, length= 45)
    private String email;
    @Column(nullable = false, length= 64)
    private String password;
    @Column(nullable = true,length = 64)
    private String address;
    @Column(name = "reset_password_token" )
    private String resetPasswordToken;
    
    @Column(columnDefinition = "Text", length = 500 )
    private String education;
    @Column(columnDefinition = "Text", length = 500)
    private String bio;
    @Column(nullable = true,length = 45)
    private String profession;
    @Lob
    @Column(nullable =true, name = "profile_pic")
    private byte[] profilepic;

    private boolean enabled;
    
    @Column(name ="verification_code", updatable = false)
    private String verificationcode;
    
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getFirstName(){
        return firstname;
    }
    public void setFirstName( String firstname){
        this.firstname = firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public void setLastName( String lastname){
        this.lastname = lastname;
    }
    @Transient
    public String getFullName(){
        return firstname+" "+lastname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail( String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword( String password){
        this.password = password;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress( String address){
        this.address = address;
    }
    
    public String getProfession(){
        return profession;
    }
    public void setProfession( String profession){
        this.profession = profession;
    }
    public String getEducation(){
        return education;
    }
    public void setEducation( String education){
        this.education = education;
    }
    public String getBio(){
        return bio;
    }
    public void setBio( String bio){
        this.bio = bio;
    }
    public byte[] getProfilePic(){
        return profilepic;
    }
    public void setProfilePic( byte[] profilepic){
        this.profilepic = profilepic;
    }
    public boolean isEnabled(){
        return enabled;
    }
    public void setEnabled( boolean enabled){
        this.enabled= enabled;
    }
    public String getVerificationCode(){
        return verificationcode;
    }
    public void setVerificationCode( String verificationcode){
        this.verificationcode = verificationcode;
    }
    public String getResetPasswordToken(){
        return resetPasswordToken;
    }
    public void setResetPasswordToken( String resetPasswordToken){
        this.resetPasswordToken = resetPasswordToken;
    }
    
    
}

