package com.sgcc.entity.event;

import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.producer.PrebookProducer;
import com.sgcc.repository.PreBooksRepository;
import com.sgcc.repository.PrebookRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrebookEventEntity {
    @Autowired
    private PrebookRedisRepository prebookRedisRepository;

    @Autowired
    private PrebookProducer prebookProducer;

    @Autowired
    private PreBooksRepository preBooksRepository;

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
        preBooksRepository.addPreBook(new ArrayList<PreBookDao>() {{
            add(preBookDao);
        }});
    }

    /**
     * 修改mysql中的预约信息
     *
     * @param prebookDTO
     */
    public PreBookDao updatePrebook(PrebookDTO prebookDTO) {
        List<PreBookDao> preBookDaos = preBooksRepository.updatePreBook(prebookDTO);
        if (null != preBookDaos && preBookDaos.size() == 1 && null != preBookDaos.get(0)) {
            return preBookDaos.get(0);
        } else {
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
        List<String> id = preBooksRepository.deletePrebook(prebookCode);
        if (null != id && id.size() == 1 && !Strings.isNullOrEmpty(id.get(0))) {
            return id.get(0);
        } else {
            return null;
        }

    }


    /**
     * 作废mysql中的预约信息
     *
     * @param prebookCodes
     * @return
     */
    public List<String> deletePrebooks(List<String> prebookCodes) {
        List<String> ids = preBooksRepository.deletePrebooks(prebookCodes);
        if (null != ids && ids.size() > 0) {
            return ids;
        } else {
            return null;
        }

    }



    /**
     * 删除redis中的预约信息
     *
     * @param id
     */
    public void deleteInRedis(String id) {
        try {
            prebookRedisRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
