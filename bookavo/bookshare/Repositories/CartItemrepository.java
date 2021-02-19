package com.bookavo.bookshare.Repositories;

import com.bookavo.bookshare.Models.CartItem;
import com.bookavo.bookshare.Models.Engg;
import com.bookavo.bookshare.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CartItemrepository extends JpaRepository<CartItem, Integer> {
    public List<CartItem> findByUser(User user);
    
    public CartItem findByUserAndEngg(User user, Engg engg);

    @Query("DELETE FROM CartItem c WHERE c.user.id = ?1 AND c.engg.id = ?2")
    @Modifying
    public void deleteByUserAndEngg(Long userId, Integer enggId);
}
