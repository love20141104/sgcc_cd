package com.sgcc.entity.query;

import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.repository.PreBookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrebookInfoQueryEntity {

    @Autowired
    private PreBookInfoRepository preBookInfoRepository;

    public int getBlacklistByOpenId(String openId){
        return preBookInfoRepository.getBlacklistByOpenId(openId);
    }

    public CheckerInfoDao getCheckerInfo(String hallName){
        return preBookInfoRepository.getCheckerInfo(hallName);
    }

    public List<PrebookInfoDao> getPrebookInfo(String openId, int status){
        return preBookInfoRepository.getPrebookInfo(openId,status);
    }


    public PrebookInfoDao getPrebookInfoDetail(String id){
        return preBookInfoRepository.getPrebookInfoDetail(id);
    }

    public CheckerInfoDao getCheckerInfoById(String id){
        return preBookInfoRepository.getCheckerInfoById(id);

    }




    public List<PrebookInfoDao> getCheckList(String hallName,int status){
        return preBookInfoRepository.getCheckList(hallName,status);
    }

    public CheckerInfoDao getCheckerByOpenId(String openId){
        return preBookInfoRepository.getCheckerByOpenId(openId);
    }



    public PrebookInfoDao getCheckDetailList(String id){
        return preBookInfoRepository.getCheckDetailList(id);
    }



}
