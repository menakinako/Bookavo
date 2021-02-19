package com.bookavo.bookshare.Models;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Lob;

import javax.persistence.Table;
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true,length = 64)
    
    private String address;
    @Column(columnDefinition = "Text", length = 500 )
    private String education;
    @Column(columnDefinition = "Text", length = 500)
    private String bio;
    @Column(nullable = true,length = 45)
    private String profession;
    @Lob
    @Column(nullable =true, name = "profile_pic")
    private byte[] profilepic;
    

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
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
    
}
