package com.bookavo.bookshare;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path enggUploadDir = Paths.get("./Engg-images");
        String enggUploadPath =enggUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/Engg-images/**").addResourceLocations("file:/" + enggUploadPath +"/" );
    }
    
}
