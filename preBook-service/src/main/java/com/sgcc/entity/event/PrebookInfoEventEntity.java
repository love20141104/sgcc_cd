package com.sgcc.entity.event;

import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookHouseholdDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.repository.PreBookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public int[] updatePrintStatus(List<String> ids){
        return preBookInfoRepository.updatePrintStatus(ids);
    }


    public int[] updateTicketStatus(List<String> ids){
        return preBookInfoRepository.updateTicketStatus(ids);
    }

    public int addChecker(CheckerInfoDao dao){
        return preBookInfoRepository.addChecker(dao);
    }

    public int updateChecker(CheckerInfoDao dao){
        return preBookInfoRepository.updateChecker(dao);
    }

    public void delChecker(List<String> ids){
        preBookInfoRepository.delChecker(ids);
    }


    public void addBlacklist(List<BlacklistDao> blacklistDaos){
        preBookInfoRepository.addBlacklist(blacklistDaos);
    }


    public int[] updatePrebookBlacklist(List<String> ids){
        return preBookInfoRepository.updatePrebookBlacklist(ids);
    }

    public void addHouseHold(List<PreBookHouseholdDao> daos){
        preBookInfoRepository.addHouseHold(daos);
    }

//    public TaxTicketBookingDao addTaxTicketBooking(TaxTicketBookingDao taxTicketBookingDao) {
//        return preBookInfoRepository.addTaxTicketBooking(taxTicketBookingDao);
//    }



}
