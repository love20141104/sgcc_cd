package com.sgcc.model;

import com.alibaba.fastjson.JSONObject;
import com.example.Utils;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.*;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class LineUpModel {

    private static final String APP_ID="axlz9zv2p02v03t4ks";
    private static final String DEVICE_ID="6722d35aa124a82d";
    private static final String HALL_ID="123456";
    private static final String BUSINESS_ID="11111";
    private static final String PRIVATE_KEY="009322d3d8d62e018ff688c7226f9719eeade1371b95a00825676c1822d370a4ab";


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
                HALL_ID,
                BUSINESS_ID,
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
                APP_ID,
                DEVICE_ID,
                null
        );

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                APP_ID,
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
        data.put("hallId",HALL_ID);
        data.put("busiId",BUSINESS_ID);
        data.put("phone",dto.getPhone());

        InputDataDTO inputDataDTO = new InputDataDTO(
                "onlineQueuing",
                APP_ID,
                DEVICE_ID,
                data
        );

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                APP_ID,
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
        data.put("hallId",HALL_ID);
        data.put("lineUpNo",dto.getLineUpNo());

        BasicInputDTO basicInputDTO = new BasicInputDTO(
                APP_ID,
                new InputDataDTO("lineUpQuery",
                        APP_ID,
                        DEVICE_ID,
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


    public List<LineUpViewDao> getAllRecordsTrans(List<LineUpDao> daos) {
        List<LineUpViewDao> lineUpViewDaos = new ArrayList<>();
        daos.forEach(dao->{
            LineUpViewDao lineUpViewDao = new LineUpViewDao();
            BeanUtils.copyProperties(dao,lineUpViewDao);
            lineUpViewDao.setLineUpTime(Utils.GetTime(dao.getLineUpTime()));
            lineUpViewDao.setSubmitDate(Utils.GetTime(dao.getSubmitDate()));
            lineUpViewDaos.add(lineUpViewDao);
        });
        return lineUpViewDaos;
    }



}
