package com.sgcc.entity.query;

import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InhabitantQueryEntity {

    @Autowired
    private UserRepository userRepository;


    public List<InhabitantInfoCorrectDao> findAll(){
        return userRepository.findInhabitantAll();
    }




}
