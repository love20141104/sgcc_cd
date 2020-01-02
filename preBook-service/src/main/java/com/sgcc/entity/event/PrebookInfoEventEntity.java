package com.sgcc.entity.event;

import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.repository.PreBookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrebookInfoEventEntity {

    @Autowired
    private PreBookInfoRepository preBookInfoRepository;

    public PrebookInfoDao addPrebook(PrebookInfoDao dao){
        return preBookInfoRepository.addPrebook(dao);
    }

    public PrebookInfoDao updateCheckPrebook(PrebookInfoDao dao){
        return preBookInfoRepository.updateCheckPrebook(dao);
    }

    public int updateLineUp(PrebookInfoDao dao){
        return preBookInfoRepository.updateLineUp(dao);
    }






}
