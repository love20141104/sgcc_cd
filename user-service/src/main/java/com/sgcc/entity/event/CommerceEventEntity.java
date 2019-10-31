package com.sgcc.entity.event;

import com.sgcc.dao.CommerceInfoCorrectDao;
import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommerceEventEntity {

    @Autowired
    private UserRepository userRepository;


    public int addCommerceInfoCorrectOrder(CommerceInfoCorrectDao dao){
        return userRepository.addCommerceInfoCorrectOrder(dao);
    }


    public int updateCommerceInfoCorrectOrder(CommerceInfoCorrectDao dao){
        return userRepository.updateCommerceInfoCorrectOrder(dao);
    }


    public int delCommerceInfoCorrectOrder(List<String> ids){
        return userRepository.delCommerceInfoCorrectOrder(ids);
    }

}
