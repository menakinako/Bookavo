package com.bookavo.bookshare.Models;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "engg")
public class Engg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length= 45)
    private String book;
    @Column(nullable = false, length= 45)
    private String description;
    @Column(nullable = false,  length= 100)
    private String lender;
    @Column(nullable = false, length= 45)
    private String category;
    @Column(nullable = false, length= 45)
    private String image;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getBook(){
        return book;
    }
    public void setBook( String book){
        this.book = book;
    }
    
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getLender(){
        return lender;
    }
    public void setLender(String lender){
        this.lender = lender;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory( String category){
        this.category = category;
    }

    public String  getImage(){
        return image;
    }
    public void setImage(String image){
        this.image = image;
    }
    @Transient
    public String getEnggImagePath(){
        if (image == null || id == 0) {
        return null;}
        return "/Engg-images/" + id +"/" + image;
    }
}
