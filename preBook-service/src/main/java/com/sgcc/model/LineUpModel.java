package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.LineUpDao;
import com.sgcc.dto.OnlineQueuingInputDTO;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class LineUpModel {


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





}
