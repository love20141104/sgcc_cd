package com.sgcc.model;

import com.example.IDUtil;
import com.example.Utils;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.*;
import com.sgcc.utils.DateUtils;

import javax.rmi.CORBA.Util;
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

        PrebookInfoDao prebookInfoDao = new PrebookInfoDao(
                UUID.randomUUID().toString(),
                dto.getUserOpenId(),
                dto.getBusinessTypeId(),
                dto.getBusinessTypeName(),
                dto.getServiceHallId(),
                dto.getServiceHallName(),
                dto.getHouseholdNo(),
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
                IDUtil.generate12NumId()

        );

        return prebookInfoDao;
    }


    public List<PrebookInfoViewDTO> getPrebookInfoTrans(List<PrebookInfoDao> prebookInfoDaos) {

        List<PrebookInfoViewDTO> prebookInfoViewDTOS = new ArrayList<>();

        prebookInfoDaos.forEach(dao -> {
            prebookInfoViewDTOS.add(new PrebookInfoViewDTO(
                    dao.getId(),
                    dao.getPrebookNo(),
                    dao.getBusinessTypeName(),
                    dao.getContact(),
                    dao.getContactTel(),
                    Utils.GetTime(dao.getSubmitDate())
            ));
        });
        return prebookInfoViewDTOS;
    }



    public PrebookDetailViewDTO getPrebookInfoDetailTrans(PrebookInfoDao prebookInfoDao,String checkName,LineUpInfoOutDTO lineUpInfoDTO) {

        String prebookDate = DateUtils.assembleDate(prebookInfoDao.getStartDate(),prebookInfoDao.getEndDate());

        PrebookDetailViewDTO prebookDetailViewDTO = new PrebookDetailViewDTO(
                prebookInfoDao.getPrebookNo(),
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                prebookInfoDao.getHouseholdNo(),
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                lineUpInfoDTO == null ? 0 : Integer.parseInt(lineUpInfoDTO.getData().get("waitingNum")),
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName,
                prebookDate

        );
        return prebookDetailViewDTO;
    }


    public PrebookDetailViewDTO getCheckDetailListTrans(PrebookInfoDao prebookInfoDao, String checkName) {
        PrebookDetailViewDTO prebookDetailViewDTO =null;
        String prebookDate = DateUtils.assembleDate(prebookInfoDao.getStartDate(),prebookInfoDao.getEndDate());

        prebookDetailViewDTO = new PrebookDetailViewDTO(
                prebookInfoDao.getPrebookNo(),
                prebookInfoDao.getBusinessTypeName(),
                prebookInfoDao.getServiceHallName(),
                prebookInfoDao.getHouseholdNo(),
                prebookInfoDao.getLineupNo(),
                prebookInfoDao.getLineupTime() == null?null:Utils.GetTime(prebookInfoDao.getLineupTime()),
                null,
                prebookInfoDao.getContact(),
                prebookInfoDao.getContactTel(),
                Utils.GetTime(prebookInfoDao.getSubmitDate()),
                prebookInfoDao.getStatus(),
                prebookInfoDao.getRejectReason(),
                checkName,
                prebookDate

        );

        return prebookDetailViewDTO;

    }


    public PrebookInfoDao updateCheckPrebookTrans(PrebookInfoEditDTO dto) {
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
                dto.getUserOpenId(),
                null,
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




}
