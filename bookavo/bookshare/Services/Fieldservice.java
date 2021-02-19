package com.bookavo.bookshare.Services;

import javax.transaction.Transactional;

import com.bookavo.bookshare.Models.Engg;
import com.bookavo.bookshare.Repositories.Enggrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class Fieldservice {
    @Autowired
    Enggrepository enggrepo;

    public Page<Engg> listAll(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1, 5);
        return enggrepo.findAll(pageable);
    }

    public Engg save( Engg engg){
        return enggrepo.save(engg);

    }
    
}
