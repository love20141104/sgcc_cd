package com.sgcc.model;

import com.example.IDUtil;
import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.BlacklistDao;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookHouseholdDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;
import com.sgcc.utils.DateUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class PrebookModel {


    public BasicInfoDTO getBasicInfoTrans(List<HallInfoDTO> hallInfoDTOS,
                                          LineUpInfoOutDTO lineUpInfoDTO,
                                          Map<String,String> deviceInfo, boolean flag) {
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO();
        basicInfoDTO.setHallInfoDTOS(hallInfoDTOS);
        basicInfoDTO.setLineup(lineUpInfoDTO.getData());
        basicInfoDTO.setDeviceInfo(deviceInfo);
        basicInfoDTO.setBlacklistFlag(flag);
        return basicInfoDTO;
    }


    public PrebookInfoDao addPrebookTrans(PrebookInfoSubmitDTO dto,Date startDate,Date endDate) {
        String uuid = UUID.randomUUID().toString();
        PrebookInfoDao prebookInfoDao = new PrebookInfoDao(
                uuid,
                dto.getUserOpenId(),
                dto.getBusinessTypeId(),
                dto.getBusinessTypeName(),
                dto.getServiceHallId(),
                dto.getServiceHallName(),
                DateUtils.assembleMonth(dto.getTicketMonth()),
                null,
                null,
                dto.getContact(),
                dto.getContactTel(),
                new Date(),
                1,
                null,
                null,
                startDate,
                endDate,
                0,
                IDUtil.generate12NumId(),
                false,
                null,
                false,
                false,
                null
        );

        return prebookInfoDao;
    }


    public List<PrebookInfoViewDTO> getPrebookInfoTrans(List<PrebookInfoDao> prebookInfoDaos) {

        List<PrebookInfoViewDTO> prebookInfoViewDTOS = new ArrayList<>();

        prebookInfoDaos.forEach(dao -> {
            prebookInfoViewDTOS.add(new PrebookInfoViewDTO(
                    dao.getId(),
                    dao.getPrebookNo(),
                    Strings.isNullOrEmpty(dao.getLineupNo()) ? null : dao.getLineupNo(),
                    dao.getContact(),
                    dao.getContactTel(),
                    Utils.GetTime(dao.getSubmitDate())
            ));
        });
        return prebookInfoViewDTOS;
    }



    public PrebookDetailViewDTO getPrebookInfoDetailTrans(PrebookInfoDao prebookInfoDao,
                                                          String checkName,
                                                          List<PreBookHouseholdDao> daos) {

        String prebookDate = DateUtils.assembleDate(prebookInfoDao.getStartDate(),prebookInfoDao.getEndDate());
        List<HouseHoldDTO> householdNos = new ArrayList<>();
        daos.forEach(dao->{
            householdNos.add(new HouseHoldDTO(
                    dao.getHouseHoldName(),
                    dao.getHouseHoldNumber(),
                    dao.getIsBatchNumber()
            ));
        });
        PrebookDetailViewDTO prebookDetailViewDTO = new PrebookDetailViewDTO(
                DateUtils.splitMonth(prebookInfoDao.getTicketMonth()),
                prebookInfoDao.getPrebookNo(),
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                householdNos,
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName,
                prebookDate,
                prebookInfoDao.getIsPrinted(),
                prebookInfoDao.getCheckDate() == null ? null : Utils.GetTime(prebookInfoDao.getCheckDate())
        );
        return prebookDetailViewDTO;
    }


    public PrebookDetailViewDTO getCheckDetailListTrans(PrebookInfoDao prebookInfoDao,
                                                        String checkName,
                                                        List<PreBookHouseholdDao> daos) {
        PrebookDetailViewDTO prebookDetailViewDTO =null;
        String prebookDate = DateUtils.assembleDate(prebookInfoDao.getStartDate(),prebookInfoDao.getEndDate());
        List<HouseHoldDTO> householdNos = new ArrayList<>();
        daos.forEach(dao->{
            householdNos.add(new HouseHoldDTO(
                    dao.getHouseHoldName(),
                    dao.getHouseHoldNumber(),
                    dao.getIsBatchNumber()
            ));
        });

        prebookDetailViewDTO = new PrebookDetailViewDTO(
                DateUtils.splitMonth(prebookInfoDao.getTicketMonth()),
                prebookInfoDao.getPrebookNo(),
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                householdNos,
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName,
                prebookDate,
                prebookInfoDao.getIsPrinted(),
                prebookInfoDao.getCheckDate() == null ? null : Utils.GetTime(prebookInfoDao.getCheckDate())

        );

        return prebookDetailViewDTO;

    }


    public PrebookInfoDao updateCheckPrebookTrans(PrebookInfoEditDTO dto,CheckerInfoDao infoDao) {
        PrebookInfoDao prebookInfoDao = new PrebookInfoDao(
                dto.getId(),
                null,
                null,
                null,
                null,
               null,
                null,
                null,
                null,
                null,
                null,
                null,
                dto.getStatus(),
                dto.getRejectReason(),
                infoDao.getId(),
                null,
                null,
                null,
                null,
                null,
                new Date(),
                null,
                null,
                null

        );

        return prebookInfoDao;
    }


    public CheckerInfoDao addCheckerTrans(CheckerSubmitDTO checkerSubmitDTO) {
        CheckerInfoDao checkerInfoDao = new CheckerInfoDao(
                UUID.randomUUID().toString(),
                checkerSubmitDTO.getCheckerName(),
                checkerSubmitDTO.getCheckerTel(),
                checkerSubmitDTO.getUserOpenId(),
                checkerSubmitDTO.getServiceHallId(),
                checkerSubmitDTO.getServiceHallName()
        );
        return checkerInfoDao;
    }


    public CheckerInfoDao updateCheckerTrans(CheckerEditDTO checkerEditDTO) {
        CheckerInfoDao checkerInfoDao = new CheckerInfoDao(
                checkerEditDTO.getId(),
                checkerEditDTO.getCheckerName(),
                checkerEditDTO.getCheckerTel(),
                checkerEditDTO.getUserOpenId(),
                null,
                null
        );
        return checkerInfoDao;
    }


    public List<CheckerViewDTO> getAllCheckersTrans(List<CheckerInfoDao> checkerInfoDaos) {

        List<CheckerViewDTO> checkerViewDTOS = new ArrayList<>();
        checkerInfoDaos.forEach(dao -> {
            checkerViewDTOS.add(new CheckerViewDTO(
                    dao.getId(),
                    dao.getCheckerName(),
                    dao.getCheckTel(),
                    dao.getUserOpenId(),
                    dao.getServiceHallId(),
                    dao.getServiceHallName()
            ));
        });
        return checkerViewDTOS;
    }


    public List<PrebookInfoDao> advanceSendMessageTrans(List<PrebookInfoDao> prebookInfoDaos, String startDate) {
        List<PrebookInfoDao> prebookDaos = new ArrayList<>();
        Date startTime = Utils.GetDate(startDate);
        prebookInfoDaos.forEach(dao -> {
            if (startTime.getTime() == dao.getStartDate().getTime()){
                prebookDaos.add(dao);
            }

        });
        return prebookDaos;
    }





    public List<PrebookInfoDao> getPrebookSizeTrans(List<PrebookInfoDao> prebookInfoDaoList, Date startDate) {

        List<PrebookInfoDao> prebookInfoDaos = new ArrayList<>();

        prebookInfoDaoList.forEach(dao -> {
            if (Utils.GetTimeForYMD(dao.getStartDate()).equals(Utils.GetTimeForYMD(startDate))){
                prebookInfoDaos.add(dao);
            }
        });
        return prebookInfoDaos;
    }


//    public List<PrebookListViewDTO> getAllPrebookTrans(List<PrebookInfoDao> prebookInfoDaos) {
//        List<PrebookListViewDTO> dtos = new ArrayList<>();
//        prebookInfoDaos.forEach(dao -> {
//            dtos.add(new PrebookListViewDTO(
//                   dao.getId(),
//                    dao.getUserOpenId(),
//                    dao.getBusinessTypeId(),
//                    dao.getBusinessTypeName(),
//                    dao.getServiceHallId(),
//                    dao.getServiceHallName(),
//                    dao.getHouseholdNo(),
//                    dao.getLineupNo(),
//                    dao.getLineupTime()== null ? null : Utils.GetTime(dao.getLineupTime()),
//                    dao.getContact(),
//                    dao.getContactTel(),
//                    Utils.GetTime(dao.getSubmitDate()),
//                    dao.getStatus(),
//                    dao.getRejectReason(),
//                    dao.getCheckerId(),
//                    Utils.GetTime(dao.getStartDate()),
//                    Utils.GetTime(dao.getEndDate()),
//                    dao.getTicketStatus(),
//                    dao.getPrebookNo()
//            ));
//        });
//        return dtos;
//    }


    public List<BlacklistDao> getNotTakeTicketListTrans(List<PrebookInfoDao> prebookInfoDaos,Date date) {

        List<BlacklistDao> blacklistDaos = new ArrayList<>();
        prebookInfoDaos.forEach(dao -> {
            if ( Utils.GetTimeForYMD(dao.getStartDate()).equals(Utils.GetTimeForYMD(date))){
                blacklistDaos.add(new BlacklistDao(
                        null,
                        dao.getUserOpenId(),
                        dao.getId(),
                        dao.getContact(),
                        dao.getContactTel(),
                        date
                ));
            }
        });
        return blacklistDaos;
    }

    public List<BlacklistDao> getNotTakeTicketListTrans(List<PrebookInfoDao> prebookInfoDaos) {
        List<BlacklistDao> blacklistDaos = new ArrayList<>();
        prebookInfoDaos.forEach(dao -> {
            blacklistDaos.add(new BlacklistDao(
                    null,
                    dao.getUserOpenId(),
                    dao.getId(),
                    dao.getContact(),
                    dao.getContactTel(),
                    new Date()
            ));

        });
        return blacklistDaos;
    }


    public List<BlacklistViewDTO> getBlacklistTrans(List<BlacklistDao> blacklistDaos) {
        List<BlacklistViewDTO> blacklistViewDTOS = new ArrayList<>();
        blacklistDaos.forEach(dao->{
            BlacklistViewDTO blacklistViewDTO = new BlacklistViewDTO();
            BeanUtils.copyProperties(dao,blacklistViewDTO);
            blacklistViewDTO.setCreateDate(Utils.GetTime(dao.getCreateDate()));
            blacklistViewDTOS.add(blacklistViewDTO);
        });
        return blacklistViewDTOS;
    }


    public List<String> updatePrebookBlacklistTrans(List<PrebookInfoDao> prebookInfoDaos) {
        List<String> ids = new ArrayList<>();
        prebookInfoDaos.forEach(dao -> {
            ids.add(dao.getId());
        });
        return ids;
    }


    public List<PreBookHouseholdDao> addHouseHoldTrans(PrebookInfoDao prebookInfoDao,PrebookInfoSubmitDTO dto) {
        List<PreBookHouseholdDao>  householdDaos = new ArrayList<>();
        dto.getHouseholdNos().forEach(no->{
            householdDaos.add(new PreBookHouseholdDao(
                    UUID.randomUUID().toString(),
                    prebookInfoDao.getId(),
                    no.getHouseholdName(),
                    no.getHouseholdNumber(),
                    no.getIsBatchNumber()
            ));
        });
        return householdDaos;
    }


    public List<List<?>> getPrebookByDateTrans(List<PrebookInfoDao> prebookInfoDaos,List<PreBookHouseholdDao> daos) {
        List<List<?>> lists = new ArrayList<>();
        List<ExportExcelDTO> completedDTOs = new ArrayList<>();
        List<ExportExcelDTO> incompletedDTOs = new ArrayList<>();

        prebookInfoDaos.forEach(dao->{
            String prebookDate = DateUtils.assembleDate(dao.getStartDate(),dao.getEndDate());
            List<HouseHoldDTO> dtos = new ArrayList<>();
            daos.forEach(d->{
                if (d.getJobId().equals(dao.getId())){
                    dtos.add(new HouseHoldDTO(
                            d.getHouseHoldName(),
                            d.getHouseHoldNumber(),
                            d.getIsBatchNumber()
                    ));
                }
            });
            if (dao.getTicketStatus() == 1){
                completedDTOs.add(new ExportExcelDTO(
                        dao.getPrebookNo(),
                        dao.getBusinessTypeName(),
                        dao.getServiceHallName(),
                        dao.getTicketMonth(),
                        dao.getLineupNo(),
                        prebookDate,
                        dao.getContact(),
                        dao.getContactTel(),
                        dao.getSubmitDate(),
                        dao.getStatus(),
                        dao.getCheckerId(),
                        dao.getCheckDate(),
                        dao.getRejectReason(),
                        dao.getIsPrinted(),
                        dao.getIsBlackList(),
                        dao.getTicketStatus(),
                        dtos
                ));
            }else {
                incompletedDTOs.add(new ExportExcelDTO(
                        dao.getPrebookNo(),
                        dao.getBusinessTypeName(),
                        dao.getServiceHallName(),
                        dao.getTicketMonth(),
                        dao.getLineupNo(),
                        prebookDate,
                        dao.getContact(),
                        dao.getContactTel(),
                        dao.getSubmitDate(),
                        dao.getStatus(),
                        dao.getCheckerId(),
                        dao.getCheckDate(),
                        dao.getRejectReason(),
                        dao.getIsPrinted(),
                        dao.getIsBlackList(),
                        dao.getTicketStatus(),
                        dtos
                ));
            }
        });
        lists.add(completedDTOs);
        lists.add(incompletedDTOs);
        return lists;
    }



}
