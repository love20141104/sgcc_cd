package com.sgcc.entity.event;

import com.sgcc.dao.InhabitantInfoCorrectDao;
import com.sgcc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InhabitantEventEntity {

    @Autowired
    private UserRepository userRepository;


    public int addInfoCorrectOrder(InhabitantInfoCorrectDao dao){
        return userRepository.addInhabitantInfoCorrectOrder(dao);
    }


    public int updateInfoCorrectOrder(InhabitantInfoCorrectDao dao){
        return userRepository.updateInhabitantInfoCorrect(dao);
    }


    public int delInfoCorrectOrder(List<String> ids){
        return userRepository.delInhabitantCorrectIds(ids);
    }

}
