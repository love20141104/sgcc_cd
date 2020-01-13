package com.sgcc.model;

import com.alibaba.fastjson.JSONObject;
import com.example.Utils;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.BasicInputDTO;
import com.sgcc.dto.InputDataDTO;
import com.sgcc.dto.OnlineQueuingInputDTO;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class LineUpModel {

    private static final String APP_ID="axlz9zv2p02v03t4ks";
    private static final String DEVICE_ID="6722d35aa124a82d";
    private static final String PRIVATE_KEY="009322d3d8d62e018ff688c7226f9719eeade1371b95a00825676c1822d370a4ab";


    public LineUpDao addLineUpTrans(Map<String, String> data, OnlineQueuingInputDTO dto) {
        LineUpDao lineUpDao = new LineUpDao(
                UUID.randomUUID().toString(),
                dto.getUserOpenId(),
                dto.getHallId(),
                dto.getBusiId(),
                dto.getContact(),
                dto.getPhone(),
                data.get("lineUpNo"),
                Utils.GetDate(data.get("lineUpTime")),
                new Date()
        );
        return lineUpDao;
    }


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




}
