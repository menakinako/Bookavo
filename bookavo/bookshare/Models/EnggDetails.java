package com.bookavo.bookshare.Models;



public class EnggDetails {
    private Engg engg;

    public  EnggDetails(Engg engg){
        this.engg = engg;
    }

    public Integer getId(){
        return engg.getId();
    }
    
    public String getBook(){
        return engg.getBook();
    }
    public String getDescription(){
        return engg.getDescription();
    }
    public String getLender(){
        return engg.getLender();
    }
    public String getImage(){
        return engg.getImage();
    }
    public String getCategory(){
        return engg.getCategory();
    }
}
