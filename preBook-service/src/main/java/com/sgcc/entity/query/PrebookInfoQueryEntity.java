package com.sgcc.entity.query;

import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookHouseholdDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.repository.PreBookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrebookInfoQueryEntity {

    @Autowired
    private PreBookInfoRepository preBookInfoRepository;


    public List<PreBookHouseholdDao> getHouseHoldByPrebookId(String id){
        return preBookInfoRepository.getHouseHoldByPrebookId(id);
    }

    public int getBlacklistByOpenId(String openId){
        return preBookInfoRepository.getBlacklistByOpenId(openId);
    }

    public CheckerInfoDao getCheckerInfo(String hallId){
        return preBookInfoRepository.getCheckerInfo(hallId);
    }

    public List<PrebookInfoDao> getPrebookInfo(String openId, int status){
        return preBookInfoRepository.getPrebookInfo(openId,status);
    }

    public List<PrebookInfoDao> getAllPrebook(){
        return preBookInfoRepository.getAllPrebook();
    }


    public List<PrebookInfoDao> getPrebookList(){
        return preBookInfoRepository.getPrebookList();
    }


    public List<PrebookInfoDao> getPrebookCount(String startDate,String endDate){
        return preBookInfoRepository.getPrebookCount(startDate,endDate);
    }

    public List<PrebookInfoDao> getPrebookSize(String openId){
        return preBookInfoRepository.getPrebookSize(openId);
    }


    public PrebookInfoDao getPrebookInfoDetail(String id){
        return preBookInfoRepository.getPrebookInfoDetail(id);
    }

    public List<CheckerInfoDao> getCheckerByServcieHallId(String hallId){
        return preBookInfoRepository.getCheckerByServcieHallId(hallId);
    }

    public PrebookInfoDao getPrebooklListById(String id){
        return preBookInfoRepository.getPrebooklListById(id);
    }

    public List<PrebookInfoDao> getPrebooklListByIds(List<String> ids){
        return preBookInfoRepository.getPrebooklListByIds(ids);
    }


    public CheckerInfoDao getCheckerInfoById(String id){
        return preBookInfoRepository.getCheckerInfoById(id);

    }

    public List<PrebookInfoDao> getNotTakeTicketList(){
        return preBookInfoRepository.getNotTakeTicketList();
    }


    public List<CheckerInfoDao> getAllCheckers(){
        return preBookInfoRepository.getAllCheckers();
    }

    public List<PrebookInfoDao> getCheckList(String hallId,int status,Boolean isPrinted){
        return preBookInfoRepository.getCheckList(hallId,status,isPrinted);
    }

    public CheckerInfoDao getCheckerById(String id){
        return preBookInfoRepository.getCheckerById(id);
    }

    public CheckerInfoDao getCheckerByOpenId(String openId){
        return preBookInfoRepository.getCheckerByOpenId(openId);
    }


    public PrebookInfoDao getCheckDetailList(String id){
        return preBookInfoRepository.getCheckDetailList(id);
    }


    public Integer getRoleByOpenId(String openId) {
        return preBookInfoRepository.getRoleByOpenId( openId);
    }

    public Integer getCountByOpenId(String openId) {
        return preBookInfoRepository.getCountByOpenId( openId);
    }

    public List<BlacklistDao> getBlacklist() {
        return preBookInfoRepository.getBlacklist();
    }
}
