package com.sgcc.entity;

import com.sgcc.dao.NoticeDao;
import com.sgcc.dao.RushRepairProgressDao;
import com.sgcc.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoticeQueryEntity {

    @Autowired
    private NoticeRepository noticeRepository;

    public List<RushRepairProgressDao> findNoticeProgress(){
        return noticeRepository.findNoticeProgress();
    }

    public List<RushRepairProgressDao> findNoticeProgressByState(Integer state){
        return noticeRepository.findNoticeProgressByState(state);
    }

    public List<NoticeDao> findNoticeInfoByDistrict(String district,String keyword){
        return noticeRepository.findNoticeList(district,keyword);
    }


    public List<NoticeDao> findNoticeListAll(){
        return noticeRepository.findNoticeListAll();
    }

    public int insertNotice(NoticeDao noticeDao){
        return noticeRepository.insertNotice(noticeDao);
    }

    public void addNoticeProgress(List<RushRepairProgressDao> daos){
        noticeRepository.addNoticeProgress(daos);
    }

    public void delNoticeProgress(String id){
        noticeRepository.delNoticeProgress(id);
    }

    public void updateNotice(NoticeDao noticeDao){
        noticeRepository.updateNotice(noticeDao);
    }


    public void delNotice(List<String> ids){
        noticeRepository.delNoticeById(ids);
    }

    public Boolean noticeOverdue(String noticeId){
        return noticeRepository.noticeOverdue( noticeId);
    }




}
