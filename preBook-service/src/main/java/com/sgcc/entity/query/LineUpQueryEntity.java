package com.sgcc.entity.query;

import com.sgcc.dao.LineUpDao;
import com.sgcc.repository.LineUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LineUpQueryEntity {

    @Autowired
    private LineUpRepository lineUpRepository;

    public List<LineUpDao> getAllRecords(){
        return lineUpRepository.getAllRecords();
    }


    public List<LineUpDao> getLineUpByOpenId(String openId){
        return lineUpRepository.getLineUpByOpenId(openId);
    }

    public List<LineUpDao> getLineUpNoByOpenId(String openId){
        return lineUpRepository.getLineUpNoByOpenId(openId);
    }


}
