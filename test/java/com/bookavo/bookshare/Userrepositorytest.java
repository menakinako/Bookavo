package com.bookavo.bookshare;

import static org.assertj.core.api.Assertions.assertThat;

import com.bookavo.bookshare.Models.User;


import com.bookavo.bookshare.Repositories.Userrepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class Userrepositorytest {
    @Autowired
    private Userrepository repo;
 
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setFirstName("alex");
        user.setLastName("hass");
        user.setEmail("alexh@gmail.com");
        user.setPassword("alex2035");

         User savedUser = repo.save(user);
         User existUser = entityManager.find(User.class, savedUser.getId());

         assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
    
    

    

    @Test
    public void testFindUserByEmail(){
        String email = "alexh@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }

 
}




