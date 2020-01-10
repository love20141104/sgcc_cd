package com.sgcc.entity.event;

import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.EncryptedDTO;
import com.sgcc.dto.LineUpInfoOutDTO;
import com.sgcc.entity.LineUpEntity;
import com.sgcc.repository.LineUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LineUpEventEntity {

    @Autowired
    private LineUpRepository lineUpRepository;

    public void addLineUp(LineUpDao dao){
        lineUpRepository.addLineUp(dao);
    }




}
