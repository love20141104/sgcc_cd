package com.sgcc.entity.event;

import com.sgcc.dao.PreBookDao;
import com.sgcc.producer.PrebookProducer;
import com.sgcc.repository.PreBookRepository;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PrebookEventEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;

    @Autowired
    private PrebookProducer prebookProducer;

    @Autowired
    private PreBookRepository preBookRepository;

    /**
     * 将preBookDao存入redis
     *
     * @param preBookDao
     */
    public void cacheSubmitPreBookDao(PreBookDao preBookDao) {
        try {
            prebookRedisRepository.save(preBookDao);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("存入redis失败");
        }

    }

    /**
     * 将preBookDao存入mysql
     *
     * @param preBookDao
     */
    public void savePrebooks(PreBookDao preBookDao) {
        preBookRepository.addPreBook(new ArrayList<PreBookDao>() {{
            add(preBookDao);
        }});
    }
}
