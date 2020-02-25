package com.sgcc.model;

import com.example.Utils;
import com.google.common.base.Strings;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.*;
import com.sgcc.utils.Constant;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class LineUpModel {


    /**
     * 新增预约记录
     * @param data
     * @param dto
     * @return
     */
    public LineUpDao addLineUpTrans(Map<String, String> data, OnlineQueuingInputDTO dto) {
        LineUpDao lineUpDao = new LineUpDao(
                UUID.randomUUID().toString(),
                dto.getUserOpenId(),
                Constant.HALL_ID,
                Constant.BUSINESS_ID,
                dto.getContact(),
                dto.getPhone(),
                data.get("lineUpNo"),
                Utils.GetDate(data.get("lineUpTime")),
                new Date()
        );
        return lineUpDao;
    }

    /**
     * 心跳
     * @return
     */
    public BasicInputDTO heartBeatTrans() {
        InputDataDTO inputDataDTO = new InputDataDTO(
                "heartbeat",
                Constant.APPID,
                Constant.DEVICE_ID,
                null
        );

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                Constant.APPID,
                inputDataDTO,
                ""
        );
        return basicInputDTO;
    }

    /**
     * 线上排队
     * @param dto
     * @return
     */
    public BasicInputDTO onlineQueuingTrans(OnlineQueuingInputDTO dto){
        Map<String,String> data = new LinkedHashMap<>();
        data.put("hallId",Constant.HALL_ID);
        data.put("busiId",Constant.BUSINESS_ID);
        data.put("phone",dto.getPhone());

        InputDataDTO inputDataDTO = new InputDataDTO(
                "onlineQueuing",
                Constant.APPID,
                Constant.DEVICE_ID,
                data
        );

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                Constant.APPID,
                inputDataDTO,
                ""
        );
        return basicInputDTO;
    }

    /**
     * 排队查询
     * @param dto
     * @return
     */
    public BasicInputDTO lineUpQueryTrans(LineUpQueryInputDTO dto){
        Map<String,String> data = new LinkedHashMap<>();
        if (!Strings.isNullOrEmpty(dto.getLineUpNo())){
            data.put("hallId",Constant.HALL_ID);
            data.put("lineUpNo",dto.getLineUpNo());
        }else {
            data.put("hallId",Constant.HALL_ID);
            data.put("busiId",Constant.BUSINESS_ID);
        }

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                Constant.APPID,
                new InputDataDTO("lineUpQuery",
                        Constant.APPID,
                        Constant.DEVICE_ID,
                        data),
                ""
        );
        return basicInputDTO;
    }



    public List<LineUpDao> getLineUpByOpenIdTrans(List<LineUpDao> lineUpDaos){
        List<LineUpDao> daos = new ArrayList<>();
        lineUpDaos.forEach(dao->{
            if ( Utils.GetTimeForYMD(dao.getSubmitDate()).equals(Utils.GetTimeForYMD(new Date()))){
                daos.add(dao);
            }
        });
        return daos;
    }


    public List<LineUpViewDTO> getAllRecordsTrans(List<LineUpDao> daos) {
        List<LineUpViewDTO> lineUpViewDTOS = new ArrayList<>();
        daos.forEach(dao->{
            LineUpViewDTO lineUpViewDTO = new LineUpViewDTO();
            BeanUtils.copyProperties(dao, lineUpViewDTO);
            lineUpViewDTO.setLineUpTime(Utils.GetTime(dao.getLineUpTime()));
            lineUpViewDTO.setSubmitDate(Utils.GetTime(dao.getSubmitDate()));
            lineUpViewDTOS.add(lineUpViewDTO);
        });
        return lineUpViewDTOS;
    }


//    public String getLineUpNoByOpenIdTrans(List<LineUpDao> daos) {
//        String lineUpNo = "";
//        daos.forEach(dao->{
//            if ( Utils.GetTimeForYMD(dao.getSubmitDate()).equals(Utils.GetTimeForYMD(new Date()))){
//                lineUpNo += dao.getLineUpNo();
//            }
//        });
//        return lineUpNo;
//    }



}
