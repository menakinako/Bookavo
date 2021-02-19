package com.bookavo.bookshare.Models;



public class CustomerUserProfileDetails {
    private UserProfile userProfile;

    public CustomerUserProfileDetails(UserProfile userProfile){
        this.userProfile = userProfile;
    }
    

   

    public String getProfession(){
        return userProfile.getProfession();
    }
    public String getAddress(){
        return userProfile.getAddress();
    }
    public String getBio(){
        return userProfile.getBio();
    }
    public String getEducation(){
        return userProfile.getEducation();
    }
}
