package com.sgcc.entity.event;

import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.producer.PrebookProducer;
import com.sgcc.repository.PreBookRepository;
import com.sgcc.repository.PrebookRedisRepository;
import com.sgcc.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * 修改mysql中的预约信息
     *
     * @param prebookDTO
     */
    public String updatePrebook(PrebookDTO prebookDTO) {
        List<String> id = preBookRepository.updatePreBook(prebookDTO);
        if(null != id && id.size()==1 && !Strings.isNullOrEmpty(id.get(0))){
            return id.get(0);
        }else {
            return null;
        }
    }

    /**
     * 作废mysql中的预约信息
     *
     * @param prebookCode
     * @return
     */
    public String deletePrebook(String prebookCode) {
        List<String> id = preBookRepository.deletePrebook(prebookCode);
        if(null != id && id.size()==1 && !Strings.isNullOrEmpty(id.get(0))){
            return id.get(0);
        }else {
            return null;
        }

    }
}
