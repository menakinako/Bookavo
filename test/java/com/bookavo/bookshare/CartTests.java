package com.bookavo.bookshare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bookavo.bookshare.Models.CartItem;
import com.bookavo.bookshare.Models.Engg;
import com.bookavo.bookshare.Models.User;
import com.bookavo.bookshare.Repositories.CartItemrepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  Replace.NONE)
@Rollback(false)
public class CartTests {
    @Autowired
    private CartItemrepository cartrepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneCartItem(){
      Engg engg =  entityManager.find(Engg.class, 7);
      User user = entityManager.find(User.class, 15L);

      CartItem newItem = new CartItem();
      newItem.setUser(user);
      newItem.setEngg(engg);

       CartItem savecartItem = cartrepo.save(newItem);
       assertTrue(savecartItem.getId() > 0);

    }

    @Test
    public void testGetCartItemsByUser(){
      User user = new User();
      user.setId(15L);

      List<CartItem> cartItems = cartrepo.findByUser(user);

      assertEquals(2, cartItems.size());


    }
    
}
