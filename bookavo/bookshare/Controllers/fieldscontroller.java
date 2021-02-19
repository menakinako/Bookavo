package com.bookavo.bookshare.Controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.bookavo.bookshare.Models.Engg;
import com.bookavo.bookshare.Models.EnggDetails;
import com.bookavo.bookshare.Repositories.Enggrepository;
import com.bookavo.bookshare.Services.Fieldservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.util.StringUtils;
import java.util.*;

@Controller
public class fieldscontroller {
    @Autowired
     Fieldservice fieldservice ;
     @Autowired
    Enggrepository enggrepo;

    @GetMapping("/share.html")
    public String viewsharePage(Model model){
      model.addAttribute("engg", new Engg());
        return "share";
    }

     @PostMapping("/addnew")
     public String saveengg(@ModelAttribute(name = "engg") Engg engg, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException{
       
        String fileName =StringUtils.cleanPath(multipartFile.getOriginalFilename());
        engg.setImage(fileName);
        Engg savedEngg = fieldservice.save(engg);
        String uploadDir = "./Engg-images/" + savedEngg.getId() ;
        Path uploadPath = Paths.get(uploadDir);
        
        if(!Files.exists(uploadPath)){
          Files.createDirectories(uploadPath);
        }
        

        try{
          InputStream inputStream = multipartFile.getInputStream();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e){
          throw new IOException("Could not save uploaded file:" + fileName);
        }
        return "engg";
      }
    
     @GetMapping("/engg.html")
     public String viewengglist(Model model, @AuthenticationPrincipal EnggDetails engg){
       
       return listByPage(model, 1, engg);
     }
     @GetMapping("/page/{pageNumber}")
     public String listByPage(Model model, 
                @PathVariable("pageNumber") int currentPage, @AuthenticationPrincipal EnggDetails engg){
      Page<Engg> page = fieldservice.listAll(currentPage);
      int totalPages = page.getTotalPages();
       List<Engg> listEngg = page.getContent();

       model.addAttribute("totalPages", totalPages);
       model.addAttribute("currentPage", currentPage);
       model.addAttribute("listEngg", listEngg);
       model.addAttribute("engg", engg);
      return "engg";


     }
}
