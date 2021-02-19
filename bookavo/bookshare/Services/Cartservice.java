package com.bookavo.bookshare.Services;

import com.bookavo.bookshare.Models.CartItem;
import com.bookavo.bookshare.Models.Engg;
import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Repositories.CartItemrepository;
import com.bookavo.bookshare.Repositories.Enggrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import javax.transaction.Transactional;

@Service
@Transactional
public class Cartservice {

    @Autowired
    private CartItemrepository cartrepo;
    
    @Autowired
    private Enggrepository enggrepo;
    
    public List<CartItem> listCartitems(User user){
        return cartrepo.findByUser(user);
    }

    public void addProduct1(Integer enggId,  User user){
       

        Engg engg = enggrepo.findById(enggId).get();

         CartItem cartItem = cartrepo.findByUserAndEngg(user, engg);

         if(cartItem != null){
             System.out.println("aldready exists");
         }else{
             cartItem = new CartItem();
             cartItem.setEngg(engg);
             cartItem.setUser(user);
         }

        cartrepo.save(cartItem);
    }

    public void removeEngg(Integer enggId, User user){
        cartrepo.deleteByUserAndEngg(user.getId(), enggId);
    }
    
}
