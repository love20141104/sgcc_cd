package com.sgcc.entity.event;

import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.model.PrebookModel;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrebookEventEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;

    public void submitPrebookInfo(PrebookDTO prebookDTO) {
        //dto转dao
        PrebookModel prebookModel = new PrebookModel();

        //TODO
        prebookModel.dto2dao(new ArrayList<PrebookDTO>() {{
            add(prebookDTO);
        }});
        synchronized (this) {
        System.out.println("ENTITY:threadID : "+Thread.currentThread().getId());
            //取出数量
            if (prebookRedisRepository.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                    prebookDTO.getServiceHallId()
                    , prebookDTO.getPrebookDate()
                    , prebookDTO.getPrebookStartTime()
            ).size() > 4) {
                //TODO 超过预约限制
            } else {
                try {
                    prebookRedisRepository.saveAll(prebookModel.getPreBookDaos());
                    //TODO 发MQ 持久化
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


    }
}
